package com.pay.api.core.dao;

import com.pay.api.client.model.TradeRouteDetailDO;
import com.pay.common.core.dao.IBaseDao;

/**
 * @author chenwei
 * @date 2019-04-22
 */
public interface TradeRouteDetailDao extends IBaseDao<TradeRouteDetailDO> {

    /**
     * 根据交易路由id查询交易路由详情
     *
     * @param tradeRouteId 交易路由id
     * @return 交易路由详情
     */
    TradeRouteDetailDO selectByTradeRouteId(Long tradeRouteId);
}
