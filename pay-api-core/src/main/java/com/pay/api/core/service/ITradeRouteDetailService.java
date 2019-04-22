package com.pay.api.core.service;

import com.pay.api.client.model.TradeRouteDetailDO;

/**
 * @author chenwei
 * @date 2019-04-22
 */
public interface ITradeRouteDetailService {

    TradeRouteDetailDO findOne(Long tradeRouteId);
}
