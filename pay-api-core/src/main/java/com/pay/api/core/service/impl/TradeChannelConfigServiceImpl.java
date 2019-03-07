package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.core.service.ITradeChannelConfigService;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/7 15:16
 */
@Service
public class TradeChannelConfigServiceImpl implements ITradeChannelConfigService {


    @Override
    public TradeChannelConfigDTO getChannelConfig(String channelNumber) {
        //todo 查询通道配置

        return null;
    }
}
