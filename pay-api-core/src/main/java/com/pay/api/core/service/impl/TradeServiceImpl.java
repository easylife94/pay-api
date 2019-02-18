package com.pay.api.core.service.impl;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.core.platform.IPlatformTrade;
import com.pay.api.core.service.ITradeService;
import com.pay.api.core.utils.SpringContextUtil;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019-01-29
 */
@Service
public class TradeServiceImpl implements ITradeService {

    @Override
    public TradeHandleResultDTO tradeHandle(TradeHandleDTO tradeHandleDTO) {
        Object bean = SpringContextUtil.getBean(tradeHandleDTO.getPlatformMapped());
        if (bean instanceof IPlatformTrade) {
            IPlatformTrade platformTrade = (IPlatformTrade) bean;
            return platformTrade.trade(tradeHandleDTO);
        } else {
            TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR,"",null,null);
            return tradeHandleResultDTO;
        }
    }
}
