package com.pay.api.core.service.impl;

import com.pay.api.client.constants.*;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.platform.IPlatformTradeHandle;
import com.pay.api.core.rabbit.RabbitMqSender;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.api.core.service.ITradeService;
import com.pay.api.core.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019-01-29
 */
@Service
public class TradeServiceImpl implements ITradeService {

    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public TradeServiceImpl(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }


    @Override
    public TradeHandleResultDTO tradeHandle(TradeHandleDTO tradeHandleDTO) {
        Object bean = SpringContextUtil.getBean(tradeHandleDTO.getPlatformMapped());
        if (bean instanceof IPlatformTradeHandle) {
            IPlatformTradeHandle platformTrade = (IPlatformTradeHandle) bean;
            return platformTrade.trade(tradeHandleDTO);
        } else {
            return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "找不到平台交易处理器：" + tradeHandleDTO.getPlatformMapped(), null, null);
        }
    }

    @Override
    public void afterTradeCreate(TradeOrderDO tradeOrderDO, TradeCreateAfterDTO tradeOrderCreateAfterDTO) {
        TradeCreateMessageDTO tradeCreateMessageDTO = new TradeCreateMessageDTO(tradeOrderDO.getSysOrderNumber(),tradeOrderCreateAfterDTO.getTradeRouteId(),
                tradeOrderCreateAfterDTO.getTradeRisk(),tradeOrderCreateAfterDTO.getTradeWarn(),tradeOrderDO.getGmtCreate().getTime());
        rabbitMqSender.sendTradeCreateMessage(tradeCreateMessageDTO);
    }
}
