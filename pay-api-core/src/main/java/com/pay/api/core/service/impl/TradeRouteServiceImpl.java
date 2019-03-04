package com.pay.api.core.service.impl;

import com.pay.api.client.constants.TradeRouteRuleEnum;
import com.pay.api.client.dto.TradeRouteCreateDTO;
import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final static Logger logger = LoggerFactory.getLogger(TradeRouteServiceImpl.class);

    private final IdServiceImpl idService;
    private final TradeRouteDao tradeRouteDao;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public TradeRouteServiceImpl(IdServiceImpl idService, TradeRouteDao tradeRouteDao, IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.idService = idService;
        this.tradeRouteDao = tradeRouteDao;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    @Override
    public TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO, TradeRouteRuleEnum tradeRouteRule, BigDecimal tradeAmount) {
        TradeRouteMerchantDTO tradeRouteMerchantDTO = null;
        TradeRouteDO finalTradeRoute = null;
        //筛选商户，禁用，限额，风控，
        List<TradeRouteDO> tradeRouteDOS = tradeRouteDao.selectMemberTradableRoute(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(),
                tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType());

        logger.info("可交易路由表：{}", tradeRouteDOS);

        Iterator<TradeRouteDO> iterator = tradeRouteDOS.iterator();
        while (iterator.hasNext()) {
            TradeRouteDO next = iterator.next();
            //单笔交易限额
            if (tradeSingleAmountLimit(next, tradeAmount)) {
                iterator.remove();
                continue;
            }

            //交易频率限制
            if (tradeHighFrequency(next)) {
                iterator.remove();
            }
        }

        if (tradeRouteDOS == null || tradeRouteDOS.size() == 0) {
            return null;
        } else if (tradeRouteDOS.size() == 1) {
            finalTradeRoute = tradeRouteDOS.get(0);
        } else {
            //路由规则
            switch (tradeRouteRule) {
                case ROUND_ROBIN:
                    finalTradeRoute = roundRobin(tradeRouteDOS);
                    break;
                default:
                    throw new PayApiException("不支持交易路由规则：" + tradeRouteRule.getType());
            }
        }

        if (finalTradeRoute != null) {
            tradeRouteMerchantDTO = new TradeRouteMerchantDTO(finalTradeRoute.getId(),finalTradeRoute.getPlatformMapped(),finalTradeRoute.getPlatformId(),finalTradeRoute.getPlatformNumber(),
                    finalTradeRoute.getPlatformName(),finalTradeRoute.getChannelId(),finalTradeRoute.getChannelNumber(),finalTradeRoute.getChannelName(),finalTradeRoute.getMerchantId(),
                    finalTradeRoute.getMerchantNumber(),finalTradeRoute.getMerchantName());
        }

        return tradeRouteMerchantDTO;
    }

    /**
     * 判断路由单笔交易金额是否限额
     *
     * @param tradeRouteDO 交易路由
     * @param tradeAmount  交易金额，不能为空
     * @return 当且仅当交易金额在单笔限额区间内返回false
     */
    private Boolean tradeSingleAmountLimit(TradeRouteDO tradeRouteDO, BigDecimal tradeAmount) {
        //小于最小单笔交易金额
        boolean lessSingleMin = tradeRouteDO.getSingleTradeAmountMin() != null && tradeAmount.compareTo(tradeRouteDO.getSingleTradeAmountMin()) < 0;
        //大于最大单笔交易金额
        boolean greaterSingleMax = tradeRouteDO.getSingleTradeAmountMax() != null && tradeAmount.compareTo(tradeRouteDO.getSingleTradeAmountMax()) > 0;
        return lessSingleMin || greaterSingleMax;
    }

    /**
     * 判断路由是否交易频繁
     *
     * @param tradeRouteDO
     * @return 当交易时间间隔设置为null或者0时永远返回false。其他当且仅当最近交易时间和当前时间小于等于交易时间间隔时返回true
     */
    private Boolean tradeHighFrequency(TradeRouteDO tradeRouteDO) {
        Long tradeIntervalTime = tradeRouteDO.getTradeIntervalTime();
        if (tradeIntervalTime == null || tradeIntervalTime.equals(0)) {
            return false;
        } else {
            return (System.currentTimeMillis() - tradeIntervalTime) <= tradeIntervalTime;
        }
    }

    /**
     * 交易轮循
     *
     * @param tradeRouteDOS 交易路由列表
     * @return
     */
    private TradeRouteDO roundRobin(List<TradeRouteDO> tradeRouteDOS) {
        TradeRouteDO roundRobinTradeRouteDO = tradeRouteDOS.get(0);
        for (TradeRouteDO routeDO : tradeRouteDOS) {
            if (routeDO.getLastTradeTimestamp() == null) {
                //未交易路由直接返回
                return routeDO;
            } else {
                //排出最小交易时间戳
                if (routeDO.getLastTradeTimestamp() < roundRobinTradeRouteDO.getLastTradeTimestamp()) {
                    roundRobinTradeRouteDO = routeDO;
                }
            }
        }
        return roundRobinTradeRouteDO;
    }

    @Override
    public Boolean create(List<TradeRouteCreateDTO> tradeRouteCreateDTOS) {
        List<TradeRouteDO> tradeRouteDOS = new ArrayList<>();
        for (TradeRouteCreateDTO routeCreateDTO : tradeRouteCreateDTOS) {
            TradeRouteDO tradeRouteDO = new TradeRouteDO();
            tradeRouteDO.setId(idService.generateId());
            tradeRouteDO.setGmtCreate(new Date());
            tradeRouteDO.setIsDeleted(false);
            BeanUtils.copyProperties(routeCreateDTO, tradeRouteDO);
            tradeRouteDOS.add(tradeRouteDO);
        }
        int i = tradeRouteDao.insertBatch(tradeRouteDOS);
        if (i < tradeRouteDOS.size()) {
            logger.error("批量插入路由表异常，路由记录数：{}，插入成功记录数：{}", tradeRouteDOS.size(), i);
        }
        return true;
    }

    @Override
    public void risk(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime) {

        tradeRouteDao.updateTradeRouteStatus(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(), tradeRouteDTO.getChannelNumber(),
                tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType(), null, null, null, status, expireTime);


    }

    @Override
    public void limit(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime) {
        tradeRouteDao.updateTradeRouteStatus(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(), tradeRouteDTO.getChannelNumber(),
                tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType(), null, status, expireTime, null, null);

    }

    @Override
    public void disable(TradeRouteDTO tradeRouteDTO, Boolean status) {
        tradeRouteDao.updateTradeRouteStatus(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(), tradeRouteDTO.getChannelNumber(),
                tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType(), status, null, null, null, null);

    }

    @Override
    public Boolean limitUpdate(TradeRouteDTO tradeRouteDTO, BigDecimal singleTradeAmountMin, BigDecimal singleTradeAmountMax, BigDecimal dayTradeAmountMax, BigDecimal monthTradeAmountMax) {

        //涉及批量更新，保证只更新范围内记录

        return null;
    }
}
