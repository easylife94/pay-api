package com.pay.api.core.service.impl;

import com.pay.api.client.constants.*;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.platform.IPlatformTradeHandle;
import com.pay.api.core.rabbit.RabbitMqSender;
import com.pay.api.core.service.ITradeChannelConfigService;
import com.pay.api.core.service.ITradeMerchantConfigService;
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
    private final TradeOrderDao tradeOrderDao;
    private final ITradeChannelConfigService tradeChannelConfigService;
    private final ITradeMerchantConfigService tradeMerchantConfigService;

    @Autowired
    public TradeServiceImpl(RabbitMqSender rabbitMqSender, TradeOrderDao tradeOrderDao, ITradeChannelConfigService tradeChannelConfigService, ITradeMerchantConfigService tradeMerchantConfigService) {
        this.rabbitMqSender = rabbitMqSender;
        this.tradeOrderDao = tradeOrderDao;
        this.tradeChannelConfigService = tradeChannelConfigService;
        this.tradeMerchantConfigService = tradeMerchantConfigService;
    }


    @Override
    public TradeHandleResultDTO tradeHandle(TradeHandleDTO tradeHandleDTO) {
        Object bean = SpringContextUtil.getBean(tradeHandleDTO.getPlatformMapped());
        if (bean instanceof IPlatformTradeHandle) {
            IPlatformTradeHandle platformTrade = (IPlatformTradeHandle) bean;
            TradeChannelConfigDTO tradeChannelConfigDTO = tradeChannelConfigService.getChannelConfig(tradeHandleDTO.getChannelNumber());
            TradeMerchantConfigDTO tradeMerchantConfigDTO = tradeMerchantConfigService.getMerchantConfig(tradeHandleDTO.getMerchantNumber());
            return platformTrade.trade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
        } else {
            return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "找不到平台交易处理器：" + tradeHandleDTO.getPlatformMapped(), null, null);
        }
    }

    @Override
    public void afterTradeCreate(TradeOrderDO tradeOrderDO, TradeCreateAfterDTO tradeOrderCreateAfterDTO) {
        TradeCreateMessageDTO tradeCreateMessageDTO = new TradeCreateMessageDTO(tradeOrderDO.getSysOrderNumber(), tradeOrderCreateAfterDTO.getTradeRouteId(),
                tradeOrderCreateAfterDTO.getTradeRisk(), tradeOrderCreateAfterDTO.getTradeWarn(), tradeOrderDO.getGmtCreate().getTime());
        rabbitMqSender.sendTradeCreateMessage(tradeCreateMessageDTO);
    }

    @Override
    public TradeHandleResultDTO preOrderTrade(String sysOrderNumber) {
        //todo 预下单
        TradeOrderDO tradeOrderDO = tradeOrderDao.selectBySysOrderNumber(sysOrderNumber);
        String content = null;
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, null, content, null);
        return tradeHandleResultDTO;
    }

    @Override
    public String buildJsapiUrl(String sysOrderNumber) {
        TradeOrderDO tradeOrderDO = tradeOrderDao.selectBySysOrderNumber(sysOrderNumber);


        return null;
    }
}
