package com.pay.api.core.service;

import com.pay.api.client.model.TradeRouteDetailDO;

/**
 * @author chenwei
 * @date 2019-04-22
 */
public interface ITradeRouteDetailService {

    /**
     * 查找路由详情
     *
     * @param tradeRouteId 路由id
     * @return
     */
    TradeRouteDetailDO findOne(Long tradeRouteId);
}
