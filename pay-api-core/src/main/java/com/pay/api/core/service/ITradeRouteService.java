package com.pay.api.core.service;

import com.pay.api.client.dto.api.TradeRouteDTO;
import com.pay.api.client.dto.api.TradeRouteMerchantDTO;

import java.util.List;

/**
 * 交易路由
 *
 * @author chenwei
 * @date 2019/1/17 15:02
 */
public interface ITradeRouteService {


    /**
     * 筛选商户
     *
     * @param tradeRouteDTO
     * @return
     */
    List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO);


    /**
     * 交易限额
     * 需要处理并发
     *
     * @param tradeRouteMerchants
     */
    void tradeLimit(List<TradeRouteMerchantDTO> tradeRouteMerchants);

    /**
     * 轮循商户
     * 需要处理并发
     *
     * @param tradeRouteMerchants
     * @return
     */
    TradeRouteMerchantDTO poll(List<TradeRouteMerchantDTO> tradeRouteMerchants);
}
