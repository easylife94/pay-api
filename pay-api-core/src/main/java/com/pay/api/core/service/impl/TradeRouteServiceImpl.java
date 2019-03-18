package com.pay.api.core.service.impl;

import com.pay.api.client.constants.TradeRouteRuleEnum;
import com.pay.api.client.dto.TradeRouteCreateDTO;
import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.dto.mapper.MemberTradeRouteDTO;
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
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final static Map<String, Lock> MEMBER_TRADE_ROUTE_LOCKS = new HashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(TradeRouteServiceImpl.class);

    private final IdServiceImpl idService;
    private final TradeRouteDao tradeRouteDao;

    @Autowired
    public TradeRouteServiceImpl(IdServiceImpl idService, TradeRouteDao tradeRouteDao) {
        this.idService = idService;
        this.tradeRouteDao = tradeRouteDao;
    }

    @Override
    public TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO, TradeRouteRuleEnum tradeRouteRule, BigDecimal tradeAmount) {
        TradeRouteMerchantDTO tradeRouteMerchantDTO = null;
        //加(会员编号 + 支付渠道 + 支付方式)锁,避免并发情况下轮询规则失效
        String lockKey = tradeRouteDTO.getMemberNumber() + tradeRouteDTO.getDefrayalChannel() + tradeRouteDTO.getDefrayalType();
        Lock lock;
        if (MEMBER_TRADE_ROUTE_LOCKS.containsKey(lockKey)) {
            lock = MEMBER_TRADE_ROUTE_LOCKS.get(lockKey);
            lock.lock();
        } else {
            lock = new ReentrantLock();
            lock.lock();
            MEMBER_TRADE_ROUTE_LOCKS.put(lockKey, lock);
        }


        try {
            MemberTradeRouteDTO finalTradeRoute = tradeRouteDao.roundRobinMemberTradeRoute(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(),
                    tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType(), tradeAmount);
            if (finalTradeRoute != null) {
                //更新路由交易时间
                tradeRouteDao.updateTradeRouteTradeTime(finalTradeRoute.getId(), System.currentTimeMillis());

                tradeRouteMerchantDTO = new TradeRouteMerchantDTO(finalTradeRoute.getId(), finalTradeRoute.getPlatformMapped(), finalTradeRoute.getPlatformId(), finalTradeRoute.getPlatformNumber(),
                        finalTradeRoute.getPlatformName(), finalTradeRoute.getChannelId(), finalTradeRoute.getChannelNumber(), finalTradeRoute.getChannelName(), finalTradeRoute.getMerchantId(),
                        finalTradeRoute.getMerchantNumber(), finalTradeRoute.getMerchantName());
            }
        } finally {
            lock.unlock();
        }

        return tradeRouteMerchantDTO;
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
