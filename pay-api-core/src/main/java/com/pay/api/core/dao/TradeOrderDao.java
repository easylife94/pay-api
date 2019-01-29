package com.pay.api.core.dao;

import com.pay.api.client.model.TradeOrderDO;

public interface TradeOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrderDO record);

    int insertSelective(TradeOrderDO record);

    TradeOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrderDO record);

    int updateByPrimaryKey(TradeOrderDO record);
}