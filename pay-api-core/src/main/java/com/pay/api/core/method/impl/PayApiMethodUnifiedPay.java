package com.pay.api.core.method.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.*;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayResultDTO;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.core.method.AbstractPayApiMethod;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.api.core.service.ITradeService;
import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 统一支付方法
 *
 * @author chenwei
 * @date 2019/1/16 13:55
 */
@Component(PayApiBeanPrefix.METHOD + ApiPayMethodNames.UNIFIED_PAY)
public class PayApiMethodUnifiedPay extends AbstractPayApiMethod<ApiPayUnifiedPayDTO> {

    private static Logger logger = LoggerFactory.getLogger(PayApiMethodUnifiedPay.class);

    /**
     * 会员交易类型锁
     */
    private final static Map<String, Lock> MEMBER_TRADE_ROUTE_LOCKS = new HashMap<>();

    private static BigDecimal minTradeAmount = new BigDecimal("0.01");

    private final ITradeRouteService tradeRouteService;
    private final ITradeOrderService tradeOrderService;
    private final ITradeService tradeService;

    @Autowired
    public PayApiMethodUnifiedPay(ITradeOrderService tradeOrderService, ITradeRouteService tradeRouteService, ITradeService tradeService) {
        this.tradeOrderService = tradeOrderService;
        this.tradeRouteService = tradeRouteService;
        this.tradeService = tradeService;
    }

    @Override
    public ApiPayMethodParamsCheckResultDTO<ApiPayUnifiedPayDTO> checkParams(String content, TradeMemberDTO memberDTO) {
        ApiPayMethodParamsCheckResultDTO<ApiPayUnifiedPayDTO> checkResultDTO = new ApiPayMethodParamsCheckResultDTO<>();
        JSONObject jsonObject = JSONObject.parseObject(content);
        ApiPayUnifiedPayDTO data = jsonObject.toJavaObject(ApiPayUnifiedPayDTO.class);

        if (StringUtils.isBlank(data.getMemberOrderNumber())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[memberOrderNumber]不能为空");
            return checkResultDTO;
        } else {
            Boolean orderExisted = tradeOrderService.memberOrderExisted(memberDTO.getMemberNumber(), data.getMemberOrderNumber());
            if (orderExisted) {
                checkResultDTO.setPass(false);
                checkResultDTO.setMsg("[memberOrderNumber]会员订单号：" + data.getMemberOrderNumber() + "已存在！");
                return checkResultDTO;
            }
        }

        if (StringUtils.isBlank(data.getTradeAmount())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[tradeAmount]不能为空");
            return checkResultDTO;
        } else if (new BigDecimal(data.getTradeAmount()).compareTo(minTradeAmount) < 0) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[tradeAmount]不能小于" + minTradeAmount);
            return checkResultDTO;
        }

        if (StringUtils.isBlank(data.getCurrency())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[currency]不能为空");
            return checkResultDTO;
        } else {
            TradeOrderCurrencyEnum currencyEnum = TradeOrderCurrencyEnum.valueOf(data.getCurrency());
            if (currencyEnum == null) {
                checkResultDTO.setPass(false);
                checkResultDTO.setMsg("[currency]值‘" + data.getCurrency() + "’错误");
                return checkResultDTO;
            }
        }

        if (StringUtils.isBlank(data.getDefrayalChannel())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[defrayalChanel]不能为空");
            return checkResultDTO;
        } else {
            DefrayalChannelEnum defrayalChannelEnum = DefrayalChannelEnum.valueOf(data.getDefrayalChannel());
            if (defrayalChannelEnum == null) {
                checkResultDTO.setPass(false);
                checkResultDTO.setMsg("[defrayalChanel]值‘" + data.getDefrayalChannel() + "’错误");
                return checkResultDTO;
            }
        }

        if (StringUtils.isBlank(data.getDefrayalType())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[defrayalType]不能为空");
            return checkResultDTO;
        } else {
            DefrayalTypeEnum defrayalTypeEnum = DefrayalTypeEnum.valueOf(data.getDefrayalType());
            if (defrayalTypeEnum == null) {
                checkResultDTO.setPass(false);
                checkResultDTO.setMsg("[defrayalType]值‘" + data.getDefrayalChannel() + "’错误");
                return checkResultDTO;
            } else if (DefrayalTypeEnum.SCAN.equals(defrayalTypeEnum) && StringUtils.isBlank(data.getAuthCode())) {
                checkResultDTO.setPass(false);
                checkResultDTO.setMsg("[defrayalType]值为‘" + DefrayalTypeEnum.SCAN + "’时，[authCode]不能为空");
                return checkResultDTO;
            }
        }

        if (StringUtils.isBlank(data.getNotifyUrl())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[notifyUrl]不能为空");
            return checkResultDTO;
        }

        if (StringUtils.isBlank(data.getTitle())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[subject]不能为空");
            return checkResultDTO;
        }

        checkResultDTO.setPass(true);
        checkResultDTO.setData(data);
        return checkResultDTO;
    }

    @Transactional
    @Override
    public ApiPayMethodResultDTO realOperate(ApiPayUnifiedPayDTO apiPayUnifiedPayDTO, TradeMemberDTO memberDTO) {
        //对同一个路由(会员编号 + 支付渠道 + 支付方式)交易加锁
        String lockKey = memberDTO.getMemberNumber() + apiPayUnifiedPayDTO.getDefrayalChannel() + apiPayUnifiedPayDTO.getDefrayalType();
        Lock lock;
        if (MEMBER_TRADE_ROUTE_LOCKS.containsKey(lockKey)) {
            lock = MEMBER_TRADE_ROUTE_LOCKS.get(lockKey);
            lock.lock();
        } else {
            lock = new ReentrantLock();
            MEMBER_TRADE_ROUTE_LOCKS.put(lockKey, lock);
            lock.lock();
        }
        try {
            ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();

            DefrayalChannelEnum defrayalChannel = DefrayalChannelEnum.valueOf(apiPayUnifiedPayDTO.getDefrayalChannel());
            DefrayalTypeEnum defrayalType = DefrayalTypeEnum.valueOf(apiPayUnifiedPayDTO.getDefrayalType());

            //2.交易路由
            //2.1.筛选商户，查询范围下正常商户（未风控商户）
            //2.2.交易限额，查询通道，商户的总交易金额，单笔金额限制，最大累计金额限制（并发）
            //2.3.轮循规则，平均（降低风控），可用（最近可用，不推荐）（并发）
            TradeRouteDTO tradeRouteDTO = new TradeRouteDTO(memberDTO.getMemberNumber(), apiPayUnifiedPayDTO.getPlatformNumber(),
                    apiPayUnifiedPayDTO.getChannelNumber(), apiPayUnifiedPayDTO.getMerchantNumber(),
                    defrayalChannel.getType(), defrayalType.getType());
            TradeRouteMerchantDTO routeMerchant = tradeRouteService.route(tradeRouteDTO, TradeRouteRuleEnum.ROUND_ROBIN, new BigDecimal(apiPayUnifiedPayDTO.getTradeAmount()));
            if (routeMerchant == null) {
                logger.error("交易路由，无交易路由。会员订单号：{}", apiPayUnifiedPayDTO.getMemberOrderNumber());
                apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.FAIL);
                apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.NONE_MERCHANT_ROUTE.getCode());
                apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.NONE_MERCHANT_ROUTE.getMsg());
                return apiPayMethodResultDTO;
            }

            TradeCreateAfterDTO tradeCreateAfterDTO = new TradeCreateAfterDTO();
            TradeRouteUpdateDTO tradeRouteUpdateDTO = new TradeRouteUpdateDTO();
            tradeRouteUpdateDTO.setId(routeMerchant.getTradeRouteId());
            tradeRouteUpdateDTO.setTradeWarnTimes(routeMerchant.getTradeWarnTimes());
            tradeRouteUpdateDTO.setTradeWarnDate(routeMerchant.getTradeWarnDate());

            //3.生成订单
            TradeOrderCreateDTO tradeOrderCreateDTO = new TradeOrderCreateDTO(memberDTO.getMemberId(), memberDTO.getMemberNumber(), memberDTO.getMemberName(), memberDTO.getAgentId(), memberDTO.getAgentNumber(),
                    memberDTO.getAgentName(), memberDTO.getAgentLevel(), apiPayUnifiedPayDTO.getDefrayalChannel(), apiPayUnifiedPayDTO.getDefrayalType(), apiPayUnifiedPayDTO.getMemberOrderNumber(),
                    new BigDecimal(apiPayUnifiedPayDTO.getTradeAmount()), routeMerchant.getMerchantId(), routeMerchant.getMerchantNumber(), routeMerchant.getMerchantName(), routeMerchant.getPlatformId(),
                    routeMerchant.getPlatformMapped(), routeMerchant.getPlatformNumber(), routeMerchant.getPlatformName(), routeMerchant.getChannelId(),
                    routeMerchant.getChannelNumber(), routeMerchant.getChannelName(), apiPayUnifiedPayDTO.getTitle(), apiPayUnifiedPayDTO.getBody(), apiPayUnifiedPayDTO.getAttach());
            TradeOrderDO tradeOrder = tradeOrderService.createTradeOrder(tradeOrderCreateDTO);

            //4.交易处理
            //5.风控处理
            TradeHandleDTO tradeHandleDTO = new TradeHandleDTO(routeMerchant.getPlatformMapped(), routeMerchant.getChannelNumber(), routeMerchant.getMerchantNumber(),
                    tradeOrder.getSysOrderNumber(), tradeOrder.getTradeAmount(), defrayalChannel, defrayalType, apiPayUnifiedPayDTO.getAuthCode());
            TradeHandleResultDTO tradeHandleResultDTO = tradeService.tradeHandle(tradeHandleDTO);
            switch (tradeHandleResultDTO.getStatus()) {
                case SUCCESS:
                    tradeOrder.setPayContent(tradeHandleResultDTO.getContent());
                    tradeOrder.setPlatformOrderNumber(tradeHandleResultDTO.getPlatformOrderNumber());
                    break;
                case UNKNOWN:
                    tradeOrder.setPlatformOrderNumber(tradeHandleResultDTO.getPlatformOrderNumber());
                    break;
                case RISK:
                    tradeOrder.setTradeStatus(TradeOrderStatusEnum.CLOSED.getType());
                    tradeOrder.setCloseType(TradeOrderCloseTypeEnum.ORDER_ERROR.getType());
                    tradeOrder.setCloseCause("下单失败，命中风控。上游返回信息：" + tradeHandleResultDTO.getErrorMsg());
                    //5.1.上游返回明确商户被风控
                    tradeRouteUpdateDTO.setTradeRisk(true);
                    break;
                case ERROR:
                    tradeOrder.setTradeStatus(TradeOrderStatusEnum.CLOSED.getType());
                    tradeOrder.setCloseType(TradeOrderCloseTypeEnum.ORDER_ERROR.getType());
                    tradeOrder.setCloseCause("下单失败。上游返回信息：" + tradeHandleResultDTO.getErrorMsg());
                    //5.2.下单失败，系统内部风控预警
                    tradeRouteUpdateDTO.setTradeWarn(true);
                    break;
                default:
                    //当做未知处理
            }

            //6.保存订单
            boolean saveTradeOrder = tradeOrderService.saveTradeOrder(tradeOrder);
            if (!saveTradeOrder) {
                throw new PayApiException("保存订单失败");
            }

            //7.更新路由
            tradeRouteService.update(tradeRouteUpdateDTO);

            //7.创建返回结果对象
            ApiPayUnifiedPayResultDTO unifiedPayResultDTO = new ApiPayUnifiedPayResultDTO(tradeOrder.getSysOrderNumber(),
                    tradeOrder.getMemberOrderNumber(), DateUtils.nowTime2Str(), tradeOrder.getMemberNumber(),
                    tradeOrder.getMerchantNumber(), tradeOrder.getCurrency(), tradeOrder.getTradeStatus(),
                    tradeOrder.getPayAmount().toString(), tradeOrder.getServiceFee().toString(), tradeOrder.getPayContent());
            apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.SUCCESS);
            apiPayMethodResultDTO.setData(unifiedPayResultDTO);

            //8.下单结束后待处理
            tradeService.afterTradeCreate(tradeOrder, tradeCreateAfterDTO);
            return apiPayMethodResultDTO;
        } finally {
            lock.unlock();
        }
    }


}
