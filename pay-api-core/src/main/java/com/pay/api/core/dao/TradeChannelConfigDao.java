package com.pay.api.core.dao;

import com.pay.api.client.model.TradeChannelConfigDO;

public interface TradeChannelConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeChannelConfigDO record);

    int insertSelective(TradeChannelConfigDO record);

    TradeChannelConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeChannelConfigDO record);

    int updateByPrimaryKey(TradeChannelConfigDO record);

    /**
     * 根据通道编号获取唯一交易通道配置
     *
     * @param channelNumber 通道编号
     * @return 交易通道配置
     */
    TradeChannelConfigDO selectOneByChannelNumber(String channelNumber);
}