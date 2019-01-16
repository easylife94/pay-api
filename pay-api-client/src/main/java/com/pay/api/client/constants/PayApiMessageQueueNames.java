package com.pay.api.client.constants;

/**
 * pay-api项目的消息队列名称
 * 消息队列名称命名规则：项目+服务
 * 消息队列名称类由生产者创建，消费者依赖
 *
 * @author chenwei
 * @date 2018-12-16
 */
public class PayApiMessageQueueNames {


    /**
     * 消息队列前缀
     */
    private static final String QUEUE_PREFIX = "pay.api.";

    /**
     * 测试消息队列
     */
    public static final String QUEUE_TEST = QUEUE_PREFIX + "test";
}
