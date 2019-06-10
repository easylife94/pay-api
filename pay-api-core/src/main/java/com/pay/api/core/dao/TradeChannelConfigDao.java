package com.pay.api.core.dao;

import com.pay.api.client.model.TradeChannelConfigDO;
import com.pay.common.core.dao.IBaseDao;

/**
 * @author chenwei
 */
public interface TradeChannelConfigDao extends IBaseDao<TradeChannelConfigDO> {

    /**
     * 根据通道编号获取唯一交易通道配置
     *
     * @param channelNumber 通道编号
     * @return 交易通道配置
     */
    TradeChannelConfigDO selectOneByChannelNumber(String channelNumber);
}