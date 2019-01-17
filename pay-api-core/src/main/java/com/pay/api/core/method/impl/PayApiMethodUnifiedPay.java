package com.pay.api.core.method.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.api.ApiPayMethodResultDTO;
import com.pay.api.client.dto.api.TradeRouteDTO;
import com.pay.api.client.dto.api.TradeRouteMerchantDTO;
import com.pay.api.client.dto.api.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.dto.api.method.ApiPayUnifiedPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.ITradeRouteService;
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

    @Autowired
    private ITradeRouteService tradeRouteService;

    /**
     * @param content 请求参数内容
     * @return
     */
    @Override
    public ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> operate(String content) {
        //TODO 统一支付方法
        ApiPayMethodResultDTO<ApiPayUnifiedPayResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO();
        JSONObject json = JSONObject.parseObject(content);
        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = json.toJavaObject(ApiPayUnifiedPayDTO.class);
        logger.info("统一支付,请求参数：{}", apiPayMethodResultDTO);

        //1.参数校验 TODO


        //2.交易路由
        //2.1.筛选商户，查询范围下正常商户（未风控商户）
        //2.2.交易限额，查询通道，商户的总交易金额，单笔金额限制，最大累计金额限制（并发）
        //2.2.轮循规则，平均（降低风控），可用（最近可用，不推荐）（并发）
        TradeRouteDTO tradeRouteDTO = new TradeRouteDTO();
        tradeRouteDTO.setPlatformNumber(apiPayUnifiedPayDTO.getPlatformNumber());
        tradeRouteDTO.setChannelNumber(apiPayUnifiedPayDTO.getChannelNumber());
        tradeRouteDTO.setMerchantNumber(apiPayUnifiedPayDTO.getMerchantNumber());
        List<TradeRouteMerchantDTO> tradeRouteMerchants = tradeRouteService.filterMerchant(tradeRouteDTO);
        tradeRouteService.tradeLimit(tradeRouteMerchants);
        TradeRouteMerchantDTO finalMerchant = tradeRouteService.poll(tradeRouteMerchants);

        //3.风控校验 TODO
        //下单返回结果是否命中上游风控

        //4.生成订单 TODO


        return apiPayMethodResultDTO;
    }
}
