package com.pay.api.core.method.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.ApiPayUnifiedPayErrorEnum;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayResultDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.api.core.service.ITradeService;
import com.pay.center.client.dto.service.MemberDTO;
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

    @Autowired
    public PayApiMethodUnifiedPay(ITradeOrderService tradeOrderService, ITradeRouteService tradeRouteService, ITradeService tradeService) {
        this.tradeOrderService = tradeOrderService;
        this.tradeRouteService = tradeRouteService;
        this.tradeService = tradeService;
    }


    /**
     * @param content 请求参数内容
     * @return 方法返回结果
     */
    @Override
    public ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> operate(String content, MemberDTO memberDTO) {
        //TODO 统一支付方法
        ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();
        JSONObject json = JSONObject.parseObject(content);
        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = json.toJavaObject(ApiPayUnifiedPayDTO.class);
        logger.info("统一支付,请求参数：{}", apiPayMethodResultDTO);

        //1.参数校验 TODO
        
        //2.生成订单
        TradeOrderDO tradeOrder = tradeOrderService.createTradeOrder();

        //3.交易路由
        //3.1.筛选商户，查询范围下正常商户（未风控商户）
        //3.2.交易限额，查询通道，商户的总交易金额，单笔金额限制，最大累计金额限制（并发）
        //3.3.轮循规则，平均（降低风控），可用（最近可用，不推荐）（并发）
        TradeRouteDTO tradeRouteDTO = new TradeRouteDTO();
        tradeRouteDTO.setPlatformNumber(apiPayUnifiedPayDTO.getPlatformNumber());
        tradeRouteDTO.setChannelNumber(apiPayUnifiedPayDTO.getChannelNumber());
        tradeRouteDTO.setMerchantNumber(apiPayUnifiedPayDTO.getMerchantNumber());
        List<TradeRouteMerchantDTO> tradeRouteMerchants = tradeRouteService.filterMerchant(tradeRouteDTO);
        tradeRouteService.tradeLimit(tradeRouteMerchants);
        TradeRouteMerchantDTO finalMerchant = tradeRouteService.poll(tradeRouteMerchants);
        if (finalMerchant == null) {
            apiPayMethodResultDTO.setSubCode(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getType());
            apiPayMethodResultDTO.setSubMsg(ApiPayUnifiedPayErrorEnum.MERCHANT_DISABLE.getError());
            return apiPayMethodResultDTO;
        }

        //4.平台交易处理器处理 todo 通道交易处理器处理
        TradeHandleDTO tradeHandleDTO = new TradeHandleDTO();
        TradeHandleResultDTO tradeHandleResultDTO = tradeService.tradeHandle(tradeHandleDTO);

        //5.风控处理
        //5.1.上游返回明确商户不可用
        //5.2.下单失败，系统内部自风控

        //6.保存订单
        tradeOrderService.updateTradeOrder(tradeOrder);

        //7.创建返回结果对象
        ApiPayUnifiedPayResultDTO unifiedPayResultDTO = new ApiPayUnifiedPayResultDTO(tradeOrder.getSysOrderNumber(), tradeOrder.getMemberOrderNumber(), "", tradeOrder.getMemberNumber(),
                tradeOrder.getMerchantNumber(), tradeOrder.getCurrency(), tradeOrder.getTradeStatus(), tradeOrder.getPayAmount().toString(), tradeOrder.getServiceFee().toEngineeringString(), "");
        apiPayMethodResultDTO.setData(unifiedPayResultDTO);

        return apiPayMethodResultDTO;
    }
}
