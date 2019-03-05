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
            //1.交易路由更新
            //1.1更新路由最近交易时间
            //1.2是否交易风控、交易预警
            TradeRouteDO tradeRouteDO = tradeRouteDao.selectByPrimaryKey(tradeCreateMessageDTO.getTradeRouteId());
            if (tradeRouteDO != null) {
                tradeRouteDO.setLastTradeTimestamp(tradeCreateMessageDTO.getTradeTimestamp());

                //明确交易风控
                if(Boolean.TRUE.equals(tradeCreateMessageDTO.getTradeRisk())){
                    tradeRouteDO.setTradeRisk(true);
                    tradeRouteDO.setTradeRiskTime(new Date());
                }

                //交易预警
                if(Boolean.TRUE.equals(tradeCreateMessageDTO.getTradeWarn())){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date lastTradeWarnDate = tradeRouteDO.getTradeWarnDate();
                    Date today = new Date();
                    Integer warnTimes = 0;

                    //当天预警次数加一，最近一次预警不是当天则置为1
                    if(lastTradeWarnDate != null && StringUtils.equals(sdf.format(lastTradeWarnDate),sdf.format(today))){
                        warnTimes = tradeRouteDO.getTradeWarnTimes() + 1;
                    } else {
                        warnTimes = 1;
                    }

                    tradeRouteDO.setTradeWarnTimes(warnTimes);
                    tradeRouteDO.setTradeWarnDate(new Date());
                    //判断连续预警次数是都达到风控线
                    if(warnTimes >= warnTimesMax){
                        tradeRouteDO.setTradeRisk(true);
                        tradeRouteDO.setTradeRiskTime(new Date());
                        logger.info("交易路由预警达到最大次数，触发系统风控。sysOrderNumber:{}，tradeRouteId：{}",
                                tradeCreateMessageDTO.getSysOrderNumber(), tradeCreateMessageDTO.getTradeRouteId());
                    }
                }
                tradeRouteDO.setGmtUpdate(new Date());
                tradeRouteDao.updateByPrimaryKey(tradeRouteDO);
            } else {
                logger.error("交易路由更新失败，路由不存在，sysOrderNumber:{}，tradeRouteId：{}",
                        tradeCreateMessageDTO.getSysOrderNumber(), tradeCreateMessageDTO.getTradeRouteId());
            }

            //2.数据统计
            //todo 创建订单数据统计
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("交易创建消息处理异常，消息内容:{}，异常：{}", tradeCreateMessageDTO, e.getMessage());
        }
    }



}
