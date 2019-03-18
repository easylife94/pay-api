package com.pay.api.core.rabbit;

import com.pay.api.client.constants.PayApiMessageQueueNames;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.core.service.ITradeRouteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息队列接收器
 *
 * @author chenwei
 * @date 2019/1/15 16:34
 */
@Component
public class RabbitMqReceiver {

    public static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @Value("${trade.warn-times-max}")
    private Integer warnTimesMax;

    private final ITradeRouteService tradeRouteService;
    private final TradeRouteDao tradeRouteDao;

    @Autowired
    public RabbitMqReceiver(ITradeRouteService tradeRouteService, TradeRouteDao tradeRouteDao) {
        this.tradeRouteService = tradeRouteService;
        this.tradeRouteDao = tradeRouteDao;
    }

    @RabbitListener(queues = PayApiMessageQueueNames.QUEUE_TRADE_CREATE)
    public void tradeCreate(TradeCreateMessageDTO tradeCreateMessageDTO) {
        try {
            logger.info("收到交易创建消息：{}", tradeCreateMessageDTO);
            //1.数据统计
            //todo 创建订单数据统计
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("交易创建消息处理异常，消息内容:{}，异常：{}", tradeCreateMessageDTO, e.getMessage());
        }
    }



}
