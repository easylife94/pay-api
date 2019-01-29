package com.pay.api.core.service.impl;

import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019-01-29
 */
@Component
public class TradeOrderServiceImpl implements ITradeOrderService {

    private final TradeOrderDao tradeOrderDao;

    @Autowired
    public TradeOrderServiceImpl(TradeOrderDao tradeOrderDao) {
        this.tradeOrderDao = tradeOrderDao;
    }

    @Override
    public TradeOrderDO createTradeOrder() {
        //todo 创建订单对象
        return null;
    }

    @Override
    public boolean updateTradeOrder(TradeOrderDO tradeOrderDO) {
        //todo 更新订单
        return false;
    }
}
