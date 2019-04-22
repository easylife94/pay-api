package com.pay.api.core.service.impl;

import com.pay.api.client.model.TradeRouteDetailDO;
import com.pay.api.core.dao.TradeRouteDetailDao;
import com.pay.api.core.service.ITradeRouteDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019-04-22
 */
@Slf4j
@Service
public class TradeRouteDetailServiceImpl implements ITradeRouteDetailService {

    private final TradeRouteDetailDao tradeRouteDetailDao;

    @Autowired
    public TradeRouteDetailServiceImpl(TradeRouteDetailDao tradeRouteDetailDao) {
        this.tradeRouteDetailDao = tradeRouteDetailDao;
    }


    @Override
    public TradeRouteDetailDO findOne(Long tradeRouteId) {
        return tradeRouteDetailDao.selectByTradeRouteId(tradeRouteId);
    }
}
