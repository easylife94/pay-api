package com.pay.api.core.method.impl;

import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.ApiPayUnifiedPayErrorEnum;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayResultDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.core.method.AbstractPayApiMethod;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeRiskControlService;
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
public class PayApiMethodUnifiedPay extends AbstractPayApiMethod<ApiPayUnifiedPayDTO> {

    private static Logger logger = LoggerFactory.getLogger(PayApiMethodUnifiedPay.class);

    private final ITradeRouteService tradeRouteService;
    private final ITradeOrderService tradeOrderService;
    private final ITradeService tradeService;
    private final ITradeRiskControlService tradeRiskService;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public PayApiMethodUnifiedPay(ITradeOrderService tradeOrderService, ITradeRouteService tradeRouteService,
                                  ITradeService tradeService, ITradeRiskControlService tradeRiskService,
                                  IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.tradeOrderService = tradeOrderService;
        this.tradeRouteService = tradeRouteService;
        this.tradeService = tradeService;
        this.tradeRiskService = tradeRiskService;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    @Override
    public ApiPayMethodParamsCheckResultDTO<ApiPayUnifiedPayDTO> checkParams(String content) {
        return null;
    }

    @Override
    public ApiPayMethodResultDTO realOperate(ApiPayUnifiedPayDTO apiPayUnifiedPayDTO, MemberDTO memberDTO) {
        ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();

        //2.交易路由
        //2.1.筛选商户，查询范围下正常商户（未风控商户）
        //2.2.交易限额，查询通道，商户的总交易金额，单笔金额限制，最大累计金额限制（并发）
        //2.3.轮循规则，平均（降低风控），可用（最近可用，不推荐）（并发）
        TradeRouteDTO tradeRouteDTO = new TradeRouteDTO(apiPayUnifiedPayDTO.getPlatformNumber(),
                apiPayUnifiedPayDTO.getChannelNumber(), apiPayUnifiedPayDTO.getMerchantNumber());
//        List<TradeRouteMerchantDTO> tradeRouteMerchants = tradeRouteService.filterMerchant(tradeRouteDTO);
//        if (tradeRouteMerchants.size() == 0) {
//            logger.error("筛选商户，筛选商户列表为空。筛选参数：{}", tradeRouteDTO);
//            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.MERCHANT_NOT_FOUND.getType());
//            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.MERCHANT_NOT_FOUND.getError());
//            return apiPayMethodResultDTO;
//        }
//
//        tradeRouteService.tradeLimit(tradeRouteMerchants, apiPayUnifiedPayDTO.getDefrayalType());
//        if (tradeRouteMerchants.size() == 0) {
//            logger.error("交易限额，可交易商户列表为空。会员订单号：{}，交易金额：{}", apiPayUnifiedPayDTO.getMemberOrderNumber(), apiPayUnifiedPayDTO.getTradeAmount());
//            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getType());
//            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getError());
//            return apiPayMethodResultDTO;
//        }
//
//        TradeRouteMerchantDTO finalMerchant = tradeRouteService.poll(tradeRouteMerchants);
//        if (finalMerchant == null) {
//            logger.error("交易轮循，商户不可用。会员订单号：{}", apiPayUnifiedPayDTO.getMemberOrderNumber());
//            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getType());
//            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getError());
//            return apiPayMethodResultDTO;
//        }

        TradeRouteMerchantDTO routeMerchant = tradeRouteService.route(tradeRouteDTO);
        if(routeMerchant == null){
            logger.error("交易路由，无交易路由。会员订单号：{}", apiPayUnifiedPayDTO.getMemberOrderNumber());
            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.NONE_MERCHANT_ROUTE.getType());
            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.NONE_MERCHANT_ROUTE.getError());
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
                //5.1.上游返回明确商户被风控
                tradeRiskService.merchantRiskControl(new TradeMerchantRiskControlDTO(finalMerchant.getMerchantNumber()));
                break;
            case ERROR:
                tradeOrder.setTradeStatus(TradeOrderStatusEnum.CLOSED.getType());
                //5.2.下单失败，系统内部风控预警
                tradeRiskService.merchantRiskControlWarn(new TradeMerchantRiskControlDTO(finalMerchant.getMerchantNumber()));
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
