package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.core.service.ITradeRouteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    @Override
    public List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO) {

        //todo
        return null;
    }

    @Override
    public void tradeLimit(List<TradeRouteMerchantDTO> tradeRouteMerchants) {

        //todo

    }

    @Override
    public TradeRouteMerchantDTO poll(List<TradeRouteMerchantDTO> tradeRouteMerchants) {

        //todo


        return null;
    }
}
