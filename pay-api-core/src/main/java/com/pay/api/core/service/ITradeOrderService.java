package com.pay.api.core.service;

import com.pay.api.client.model.TradeOrderDO;

/**
 * 订单服务接口
 *
 * @author chenwei
 * @date 2019-01-29
 */
public interface ITradeOrderService {


    /**
     * 创建订单记录
     *
     * @return 订单DO
     */
    TradeOrderDO createTradeOrder();

    /**
     * 更新订单记录ØØ
     *
     * @param tradeOrderDO
     * @return 保存成功返回true
     */
    boolean updateTradeOrder(TradeOrderDO tradeOrderDO);

}
