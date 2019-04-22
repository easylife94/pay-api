package com.pay.api.core.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.PayApiMessageQueueNames;
import com.pay.api.client.dto.async.TradeCompleteMessageDTO;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.asset.client.constants.*;
import com.pay.asset.client.dto.WalletSubRecordDTO;
import com.pay.asset.client.dto.async.TradeStatisticsMessageDTO;
import com.pay.asset.client.dto.async.WalletRecordMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * rabbitMq消息发送器
 * Ø
 *
 * @author chenwei
 * @date 2018-12-16
 */
@Service
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送交易创建异步消息
     *
     * @param tradeCreateMessageDTO 异步消息
     */
    public void sendTradeCreateMessage(TradeCreateMessageDTO tradeCreateMessageDTO) {
        amqpTemplate.convertAndSend(PayApiMessageQueueNames.QUEUE_TRADE_CREATE, tradeCreateMessageDTO);
    }

    /**
     * 发送交易完成异步消息
     *
     * @param tradeCompleteMessageDTO 异步消息
     */
    public void sendTradeCompleteMessages(TradeCompleteMessageDTO tradeCompleteMessageDTO) {
        //（钱包记录、结算任务、回调任务、消息推送）
        //1.钱包记录
        WalletRecordMessageDTO walletRecordMessageDTO = new WalletRecordMessageDTO();
        walletRecordMessageDTO.setOrderStatus(WalletRecordOrderStatusEnum.PAYMENT);
        walletRecordMessageDTO.setOrderType(WalletRecordOrderTypeEnum.TRADE_ORDER);
        walletRecordMessageDTO.setOrderNumber(tradeCompleteMessageDTO.getSysOrderNumber());
        walletRecordMessageDTO.setOwnNumber(tradeCompleteMessageDTO.getSysOrderNumber());
        walletRecordMessageDTO.setOwnRole(WalletOwnRoleEnum.MEMBER);
        walletRecordMessageDTO.setOwnNumber(tradeCompleteMessageDTO.getOwnNumber());
        List<WalletSubRecordDTO> subRecords = new ArrayList<>();
        //1.1会员服务费支出
        WalletSubRecordDTO memberServiceFeeOutRecord = new WalletSubRecordDTO();
        memberServiceFeeOutRecord.setAmount(tradeCompleteMessageDTO.getServiceFee());
        memberServiceFeeOutRecord.setPaymentType(WalletRecordPaymentTypeEnum.OUT);
        memberServiceFeeOutRecord.setTradeType(WalletRecordTradeTypeEnum.TRADE_SERVICE_FEE);
        memberServiceFeeOutRecord.setTradeTime(tradeCompleteMessageDTO.getTradeTime());
        subRecords.add(memberServiceFeeOutRecord);
        walletRecordMessageDTO.setSubRecords(subRecords);

        amqpTemplate.convertAndSend(PayAssetMessageQueueNames.QUEUE_WALLET_RECORD, JSONObject.toJSONString(walletRecordMessageDTO));
        //2.结算消息
        //3.回调消息
        //4.消息推送

    }

    /**
     * 发送交易风控或预警异步消息
     */
    public void sendTradeRiskOrWarnMessage() {

    }


    /**
     * 发送交易统计异步消息
     *
     * @param tradeStatisticsMessageDTO 异步消息
     */
    public void sendTradeStatisticsMessage(TradeStatisticsMessageDTO tradeStatisticsMessageDTO) {
        amqpTemplate.convertAndSend(PayAssetMessageQueueNames.QUEUE_TRADE_STATISTICS, tradeStatisticsMessageDTO);
    }
}
