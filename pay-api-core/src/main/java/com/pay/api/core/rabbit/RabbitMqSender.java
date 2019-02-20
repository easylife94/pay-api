package com.pay.api.core.rabbit;

import com.pay.api.client.dto.async.UnifiedPayFinishMessageDTO;
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
     * 发送统一支付结束后异步消息
     *
     * @param unifiedPayFinishMessageDTO 异步消息
     */
    public void sendUnifiedPayFinishMessage(UnifiedPayFinishMessageDTO unifiedPayFinishMessageDTO) {
        amqpTemplate.convertAndSend(unifiedPayFinishMessageDTO);
    }
}
