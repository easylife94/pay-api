package com.pay.api.core.rabbit;

import com.pay.api.client.constants.PayApiMessageQueueNames;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
