package com.pay.api.web.config;


import com.pay.api.client.constants.PayApiMqNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 *
 * @author chenwei
 * @date 2018-12-12
 */
@Configuration
public class RabbitConfig {

    /**
     * test 消息队列
     *
     * @return
     */
    @Bean
    public Queue testQueue() {
        return new Queue(PayApiMqNames.QUEUE_TEST);
    }
}
