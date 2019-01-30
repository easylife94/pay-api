package com.pay.api.core.method.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.ApiPayUnifiedPayErrorEnum;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayResultDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.api.core.service.ITradeService;
import com.pay.center.client.dto.service.MemberDTO;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 统一支付方法
 *
 * @author chenwei
 * @date 2019/1/16 13:55
 */
@Component(PayApiBeanPrefix.METHOD + ApiPayMethodNames.UNIFIED_PAY)
public class PayApiMethodUnifiedPay implements IPayApiMethod {

    private static Logger logger = LoggerFactory.getLogger(PayApiMethodUnifiedPay.class);

    private final ITradeRouteService tradeRouteService;
    private final ITradeOrderService tradeOrderService;
    private final ITradeService tradeService;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public PayApiMethodUnifiedPay(ITradeOrderService tradeOrderService, ITradeRouteService tradeRouteService,
                                  ITradeService tradeService, IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.tradeOrderService = tradeOrderService;
        this.tradeRouteService = tradeRouteService;
        this.tradeService = tradeService;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    @Override
    public ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> operate(String content, MemberDTO memberDTO) {
        //TODO 统一支付方法
        ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();
        JSONObject json = JSONObject.parseObject(content);
        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = json.toJavaObject(ApiPayUnifiedPayDTO.class);
        logger.info("统一支付,请求参数：{}", apiPayMethodResultDTO);

        //1.参数校验 TODO 参数校验


        //2.交易路由
        //2.1.筛选商户，查询范围下正常商户（未风控商户）
        //2.2.交易限额，查询通道，商户的总交易金额，单笔金额限制，最大累计金额限制（并发）
        //2.3.轮循规则，平均（降低风控），可用（最近可用，不推荐）（并发）
        TradeRouteDTO tradeRouteDTO = new TradeRouteDTO(apiPayUnifiedPayDTO.getPlatformNumber(),
                apiPayUnifiedPayDTO.getChannelNumber(), apiPayUnifiedPayDTO.getMerchantNumber());
        List<TradeRouteMerchantDTO> tradeRouteMerchants = tradeRouteService.filterMerchant(tradeRouteDTO);
        tradeRouteService.tradeLimit(tradeRouteMerchants);
        TradeRouteMerchantDTO finalMerchant = tradeRouteService.poll(tradeRouteMerchants);
        if (finalMerchant == null) {
            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getType());
            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getError());
            return apiPayMethodResultDTO;
        }


        //3.生成订单
        TradeOrderCreateDTO tradeOrderCreateDTO = new TradeOrderCreateDTO(memberDTO.getMemberId(), memberDTO.getMemberNumber(),
                memberDTO.getMemberName(), memberDTO.getAgentId(), memberDTO.getAgentNumber(), memberDTO.getAgentName(), memberDTO.getAgentLevel(),
                apiPayUnifiedPayDTO.getDefrayalChannel(), apiPayUnifiedPayDTO.getDefrayalType(), apiPayUnifiedPayDTO.getMemberOrderNumber());
        TradeOrderDO tradeOrder = tradeOrderService.createTradeOrder(tradeOrderCreateDTO);

        //4.交易处理
        //5.风控处理
        TradeHandleDTO tradeHandleDTO = new TradeHandleDTO();
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
                //5.1.上游返回明确商户不可用 TODO 订单风控处理

                break;
            case ERROR:
                tradeOrder.setTradeStatus(TradeOrderStatusEnum.CLOSED.getType());
                //5.2.下单失败，系统内部自风控 TODO 订单错误处理

                break;
            default:
                //当做未知处理
        }

        //6.保存订单
        tradeOrderService.updateTradeOrder(tradeOrder);

        //7.创建返回结果对象
        ApiPayUnifiedPayResultDTO unifiedPayResultDTO = new ApiPayUnifiedPayResultDTO(tradeOrder.getSysOrderNumber(),
                tradeOrder.getMemberOrderNumber(), DateUtils.nowTime2Str(), tradeOrder.getMemberNumber(),
                tradeOrder.getMerchantNumber(), tradeOrder.getCurrency(), tradeOrder.getTradeStatus(),
                tradeOrder.getPayAmount().toString(), tradeOrder.getServiceFee().toEngineeringString(), tradeOrder.getPayContent());
        apiPayMethodResultDTO.setData(unifiedPayResultDTO);

        return apiPayMethodResultDTO;
    }
}
