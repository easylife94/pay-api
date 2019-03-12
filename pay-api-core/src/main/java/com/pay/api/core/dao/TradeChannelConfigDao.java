package com.pay.api.core.dao;

import com.pay.api.client.model.TradeChannelConfigDO;

public interface TradeChannelConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeChannelConfigDO record);

    int insertSelective(TradeChannelConfigDO record);

    TradeChannelConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeChannelConfigDO record);

    int updateByPrimaryKey(TradeChannelConfigDO record);
}