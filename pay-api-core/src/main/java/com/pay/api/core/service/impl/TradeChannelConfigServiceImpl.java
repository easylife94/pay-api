package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.model.TradeChannelConfigDO;
import com.pay.api.core.dao.TradeChannelConfigDao;
import com.pay.api.core.service.ITradeChannelConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/7 15:16
 */
@Service
public class TradeChannelConfigServiceImpl implements ITradeChannelConfigService {

    private final TradeChannelConfigDao tradeChannelConfigDao;

    @Autowired
    public TradeChannelConfigServiceImpl(TradeChannelConfigDao tradeChannelConfigDao) {
        this.tradeChannelConfigDao = tradeChannelConfigDao;
    }

    @Override
    public TradeChannelConfigDTO getChannelConfig(String channelNumber) {
        TradeChannelConfigDTO tradeChannelConfigDTO = null;
        TradeChannelConfigDO tradeChannelConfigDO = tradeChannelConfigDao.selectOneByChannelNumber(channelNumber);
        if (tradeChannelConfigDO != null) {
            tradeChannelConfigDTO = new TradeChannelConfigDTO();
            BeanUtils.copyProperties(tradeChannelConfigDO, tradeChannelConfigDTO);
        }
        return tradeChannelConfigDTO;
    }
}
