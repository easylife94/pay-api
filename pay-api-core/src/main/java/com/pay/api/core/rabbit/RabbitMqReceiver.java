package com.pay.api.core.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.PayApiMessageQueueNames;
import com.pay.api.client.dto.async.TradeCreateMessageDTO;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.asset.client.dto.async.TradeStatisticsMessageDTO;
import com.pay.common.core.utils.MoneyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${trade.warn-times-max}")
    private Integer warnTimesMax;

    private final ITradeRouteService tradeRouteService;
    private final TradeRouteDao tradeRouteDao;
    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public RabbitMqReceiver(ITradeRouteService tradeRouteService, TradeRouteDao tradeRouteDao, RabbitMqSender rabbitMqSender) {
        this.tradeRouteService = tradeRouteService;
        this.tradeRouteDao = tradeRouteDao;
        this.rabbitMqSender = rabbitMqSender;
    }

    @RabbitListener(queues = PayApiMessageQueueNames.QUEUE_TRADE_CREATE)
    public void tradeCreate(String content) {
        try {
            logger.info("收到交易创建消息：{}", content);
            TradeCreateMessageDTO tradeCreateMessageDTO = JSONObject.toJavaObject(JSONObject.parseObject(content), TradeCreateMessageDTO.class);
            //1.发送数据统计消息
            TradeStatisticsMessageDTO tradeStatisticsMessageDTO = new TradeStatisticsMessageDTO();
            BeanUtils.copyProperties(tradeCreateMessageDTO, tradeStatisticsMessageDTO);
            tradeStatisticsMessageDTO.setTradeAmount(MoneyUtils.toCentValue(tradeCreateMessageDTO.getTradeAmount()));
            tradeStatisticsMessageDTO.setTradeServiceFee(MoneyUtils.toCentValue(tradeCreateMessageDTO.getTradeServiceFee()));
            rabbitMqSender.sendTradeStatisticsMessage(tradeStatisticsMessageDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("交易创建消息处理异常，消息内容:{}，异常：{}", content, e.getMessage());
        }
    }


}
