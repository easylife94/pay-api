package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.service.client.IPayApiFeignServiceClient;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.center.client.dto.service.TradeMerchantDTO;
import com.pay.center.client.dto.service.query.TradeMerchantQueryDTO;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public TradeRouteServiceImpl(IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    @Override
    public List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO) {
        List<TradeRouteMerchantDTO> tradeRouteMerchants = new ArrayList<>();
        TradeMerchantQueryDTO query = new TradeMerchantQueryDTO(tradeRouteDTO.getPlatformNumber(),
                tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber());
        List<TradeMerchantDTO> tradeMerchants = payCenterFeignServiceClient.listTradeMerchant(query);
        if (tradeMerchants != null) {
            for (TradeMerchantDTO merchantDTO : tradeMerchants) {
                TradeRouteMerchantDTO tradeRouteMerchantDTO = new TradeRouteMerchantDTO(merchantDTO.getPlatformNumber(),
                        merchantDTO.getChannelNumber(),merchantDTO.getMerchantNumber());

                tradeRouteMerchants.add(tradeRouteMerchantDTO);
            }
        }
        return tradeRouteMerchants;
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
