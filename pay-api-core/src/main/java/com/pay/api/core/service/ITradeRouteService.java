package com.pay.api.core.service;

import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;

import java.util.List;

/**
 * 交易路由
 *
 * @author chenwei
 * @date 2019/1/17 15:02
 */
public interface ITradeRouteService {


//    /**
//     * 筛选商户
//     *
//     * @param tradeRouteDTO
//     * @return 返回不为null
//     */
//    List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO);
//
//
//    /**
//     * 交易限额
//     *
//     * @param tradeRouteMerchants
//     * @param defrayalType
//     */
//    void tradeLimit(List<TradeRouteMerchantDTO> tradeRouteMerchants, String defrayalType);
//
//    /**
//     * 轮循商户
//     * 需要处理并发
//     *
//     * @param tradeRouteMerchants
//     * @return
//     */
//    TradeRouteMerchantDTO poll(List<TradeRouteMerchantDTO> tradeRouteMerchants);


    /**
     * 交易路由
     *
     * @param tradeRouteDTO
     * @return
     */
    TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO);
}
