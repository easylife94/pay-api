package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.service.client.IPayApiFeignServiceClient;
import com.pay.api.core.service.ITradeRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final IPayApiFeignServiceClient payApiFeignServiceClient;

    @Autowired
    public TradeRouteServiceImpl(IPayApiFeignServiceClient payApiFeignServiceClient) {
        this.payApiFeignServiceClient = payApiFeignServiceClient;
    }

    @Override
    public List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO) {
        //todo 过滤商户



        return null;
    }

    @Override
    public void tradeLimit(List<TradeRouteMerchantDTO> tradeRouteMerchants) {

        //todo 交易限额

    }

    @Override
    public TradeRouteMerchantDTO poll(List<TradeRouteMerchantDTO> tradeRouteMerchants) {

        //todo 轮循


        return null;
    }
}
