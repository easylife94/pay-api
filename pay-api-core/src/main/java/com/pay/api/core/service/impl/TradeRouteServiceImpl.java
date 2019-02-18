package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeRouteCreateDTO;
import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.core.service.ITradeLimitService;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final TradeRouteDao tradeRouteDao;
    private final TradeOrderDao tradeOrderDao;
    private final ITradeLimitService tradeLimitService;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public TradeRouteServiceImpl(TradeRouteDao tradeRouteDao, TradeOrderDao tradeOrderDao, ITradeLimitService tradeLimitService,
                                 IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.tradeRouteDao = tradeRouteDao;
        this.tradeOrderDao = tradeOrderDao;
        this.tradeLimitService = tradeLimitService;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    @Override
    public TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO, BigDecimal tradeAmount) {

        //筛选商户，禁用，限额，风控，单笔交易限额
        List<TradeRouteDO> tradeRouteDOS = tradeRouteDao.selectMemberTradableRoute(tradeRouteDTO.getMemberNumber(), tradeRouteDTO.getPlatformNumber(),
                tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber(), tradeRouteDTO.getDefrayalChannelEnum().getType(),
                tradeRouteDTO.getDefrayalTypeEnum().getType(), tradeAmount);

        //轮询规则

        return null;
    }

    @Override
    public Boolean create(TradeRouteCreateDTO tradeRouteCreateDTO) {
        return null;
    }

    @Override
    public void risk(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime) {

    }

    @Override
    public void limit(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime) {

    }

    @Override
    public void disable(TradeRouteDTO tradeRouteDTO, Boolean status) {

    }

    @Override
    public Boolean limitUpdate(TradeRouteDTO tradeRouteDTO, BigDecimal singleTradeAmountMin, BigDecimal singleTradeAmountMax, BigDecimal dayTradeAmountMax, BigDecimal monthTradeAmountMax) {
        return null;
    }
}
