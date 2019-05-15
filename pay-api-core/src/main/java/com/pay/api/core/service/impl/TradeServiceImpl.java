package com.pay.api.core.service.impl;

import com.pay.api.client.constants.*;
import com.pay.api.client.dto.*;
import com.pay.api.client.dto.async.TradeCompleteMessageDTO;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.platform.IPlatformTradeHandle;
import com.pay.api.core.rabbit.RabbitMqSender;
import com.pay.api.core.service.ITradeChannelConfigService;
import com.pay.api.core.service.ITradeMerchantConfigService;
import com.pay.api.core.service.ITradeService;
import com.pay.api.core.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenwei
 * @date 2019-01-29
 */
@Service
@Slf4j
public class TradeServiceImpl implements ITradeService {

    private final RabbitMqSender rabbitMqSender;
    private final TradeOrderDao tradeOrderDao;
    private final ITradeChannelConfigService tradeChannelConfigService;
    private final ITradeMerchantConfigService tradeMerchantConfigService;

    @Autowired
    public TradeServiceImpl(RabbitMqSender rabbitMqSender, TradeOrderDao tradeOrderDao, ITradeChannelConfigService tradeChannelConfigService,
                            ITradeMerchantConfigService tradeMerchantConfigService) {
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
        TradeCreateMessageDTO tradeCreateMessageDTO = new TradeCreateMessageDTO(tradeOrderDO.getSysOrderNumber(), tradeOrderDO.getGmtCreate().getTime(),
                tradeOrderDO.getTradeAmount(),tradeOrderDO.getServiceFee(),tradeOrderDO.getTradeStatus(),tradeOrderDO.getPlatformNumber(),
                tradeOrderDO.getChannelNumber(),tradeOrderDO.getAgentNumber(),tradeOrderDO.getMemberNumber(),tradeOrderDO.getMerchantNumber(),
                tradeOrderDO.getDefrayalChannel(),tradeOrderDO.getDefrayalType());
        rabbitMqSender.sendTradeCreateMessage(tradeCreateMessageDTO);
    }

    @Override
    public TradeCompleteResultDTO complete(TradeCompleteDTO tradeCompleteDTO) {
        TradeCompleteResultDTO resultDTO = new TradeCompleteResultDTO();
        try {
            TradeOrderDO tradeOrderDO = tradeOrderDao.selectBySysOrderNumber(tradeCompleteDTO.getSysOrderNumber());
            TradeOrderStatusEnum tradeOrderStatus = TradeOrderStatusEnum.valueOf(tradeOrderDO.getTradeStatus());
            switch (tradeOrderStatus) {
                case WAIT:
                    //修改订单状态，并发送订单完成异步消息
                    tradeOrderDO.setTradeStatus(TradeOrderStatusEnum.SUCCESS.getType());
                    resultDTO.setSuccess(true);
                    tradeOrderDao.updateByCompleteTrade(tradeCompleteDTO.getSysOrderNumber(), tradeCompleteDTO.getChannelOrderNumber(),
                            tradeCompleteDTO.getSourceOrderNumber(), tradeCompleteDTO.getPayTime(), tradeCompleteDTO.getNotifyTime(), null,
                            TradeOrderStatusEnum.SUCCESS.getType(), new Date());
                    TradeCompleteMessageDTO tradeCompleteMessageDTO = new TradeCompleteMessageDTO();
                    tradeCompleteMessageDTO.setSysOrderNumber(tradeOrderDO.getSysOrderNumber());
                    tradeCompleteMessageDTO.setServiceFee(tradeOrderDO.getServiceFee());
                    tradeCompleteMessageDTO.setTradeAmount(tradeOrderDO.getTradeAmount());
                    tradeCompleteMessageDTO.setOwnNumber(tradeOrderDO.getMemberNumber());
                    tradeCompleteMessageDTO.setTradeTime(tradeOrderDO.getSysOrderTime());
                    rabbitMqSender.sendTradeCompleteMessages(tradeCompleteMessageDTO);
                    break;
                case SUCCESS:
                    log.info("订单已支付成功,订单号：{}", tradeCompleteDTO.getSysOrderNumber());
                    resultDTO.setSuccess(true);
                    break;
                default:
                    log.error("订单状态异常,订单号：{},订单状态：{}", tradeCompleteDTO.getSysOrderNumber(), tradeOrderStatus);
                    resultDTO.setSuccess(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("完成订单异常，sysOrderNumber:{},ERROR:{}", tradeCompleteDTO.getSysOrderNumber(), e.getMessage());
        }
        return resultDTO;
    }
}
