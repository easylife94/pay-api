package com.pay.api.core.service;

import com.pay.api.client.dto.TradeChannelConfigDTO;

/**
 * 交易通道配置服务接口
 *
 * @author chenwei
 * @date 2019/3/7 15:15
 */
public interface ITradeChannelConfigService {


    /**
     * 获取通道配置
     *
     * @param channelNumber 通道编号
     * @return 返回通道配置信息
     */
    TradeChannelConfigDTO getChannelConfig(String channelNumber);
}
