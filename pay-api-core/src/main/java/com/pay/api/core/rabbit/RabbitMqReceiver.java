package com.pay.api.core.rabbit;

import com.pay.center.client.constants.PayCenterMessageQueue;
import com.pay.center.client.dto.mq.RegisterMerchantMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列接收器
 *
 * @author chenwei
 * @date 2019/1/15 16:34
 */
@Component
public class RabbitMqReceiver {

    public static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitListener(queues = PayCenterMessageQueue.QUEUE_HELLO)
    public void hello(String name) {
        logger.info("hi " + name);
    }

    @RabbitListener(queues = PayCenterMessageQueue.QUEUE_REGISTER_MERCHANT)
    public void registerMerchant(RegisterMerchantMsgDTO registerMerchantMsgDTO) {
        try {
            //TODO 进件处理
            logger.info("收到进件商户消息：{}", registerMerchantMsgDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
