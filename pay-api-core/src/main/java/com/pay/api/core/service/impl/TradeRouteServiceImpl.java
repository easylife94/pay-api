package com.pay.api.core.service.impl;

import com.pay.api.client.constants.TradeRouteRuleEnum;
import com.pay.api.client.dto.TradeRouteCreateDTO;
import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.dto.TradeRouteUpdateDTO;
import com.pay.api.client.dto.mapper.MemberTradeRouteDTO;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.core.service.ITradeRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Slf4j
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    @Value("${trade.warn-times-max}")
    private Integer warnTimesMax;

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
        MemberTradeRouteDTO finalTradeRoute = tradeRouteDao.roundRobinMemberTradeRoute(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(),
                tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannel(), tradeRouteDTO.getDefrayalType(), tradeAmount);
        if (finalTradeRoute != null) {
            tradeRouteMerchantDTO = new TradeRouteMerchantDTO(finalTradeRoute.getId(), finalTradeRoute.getPlatformMapped(), finalTradeRoute.getPlatformId(), finalTradeRoute.getPlatformNumber(),
                    finalTradeRoute.getPlatformName(), finalTradeRoute.getChannelId(), finalTradeRoute.getChannelNumber(), finalTradeRoute.getChannelName(), finalTradeRoute.getMerchantId(),
                    finalTradeRoute.getMerchantNumber(), finalTradeRoute.getMerchantName(), finalTradeRoute.getTradeWarnDate(), finalTradeRoute.getTradeWarnTimes());
        }

        return tradeRouteMerchantDTO;
    }

    @Override
    public void update(TradeRouteUpdateDTO tradeRouteUpdateDTO) {
        Date now = new Date();
        TradeRouteDO tradeRouteDO = new TradeRouteDO();
        tradeRouteDO.setId(tradeRouteUpdateDTO.getId());
        //明确交易风控
        if (Boolean.TRUE.equals(tradeRouteUpdateDTO.getTradeRisk())) {
            tradeRouteDO.setTradeRisk(true);
            tradeRouteDO.setTradeRiskTime(new Date());
        }

        //交易预警
        if (Boolean.TRUE.equals(tradeRouteUpdateDTO.getTradeWarn())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date lastTradeWarnDate = tradeRouteUpdateDTO.getTradeWarnDate();
            Date today = new Date();
            Integer warnTimes;

            //当天预警次数加一，最近一次预警不是当天则置为1
            if (lastTradeWarnDate != null && StringUtils.equals(sdf.format(lastTradeWarnDate), sdf.format(today))) {
                warnTimes = tradeRouteUpdateDTO.getTradeWarnTimes() + 1;
            } else {
                warnTimes = 1;
            }

            tradeRouteDO.setTradeWarnTimes(warnTimes);
            tradeRouteDO.setTradeWarnDate(new Date());
            //判断连续预警次数是都达到风控线
            if (warnTimes >= warnTimesMax) {
                tradeRouteDO.setTradeRisk(true);
                tradeRouteDO.setTradeRiskTime(now);
                log.info("交易路由预警达到最大次数，触发系统风控。tradeRouteId：{}", tradeRouteUpdateDTO.getId());
            }
        }
        tradeRouteDO.setLastTradeTimestamp(System.currentTimeMillis());
        tradeRouteDO.setGmtUpdate(now);
        tradeRouteDao.updateByPrimaryKeySelective(tradeRouteDO);
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
            log.error("批量插入路由表异常，路由记录数：{}，插入成功记录数：{}", tradeRouteDOS.size(), i);
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
