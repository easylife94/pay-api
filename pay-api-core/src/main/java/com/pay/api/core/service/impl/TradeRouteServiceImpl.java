package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;
import com.pay.api.client.model.TradeLimitDO;
import com.pay.api.core.dao.TradeLimitDao;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.service.ITradeLimitService;
import com.pay.api.core.service.ITradeRouteService;
import com.pay.center.client.dto.service.TradeMerchantDTO;
import com.pay.center.client.dto.service.query.TradeMerchantQueryDTO;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenwei
 * @date 2019/1/17 15:07
 */
@Service
public class TradeRouteServiceImpl implements ITradeRouteService {

    private final TradeOrderDao tradeOrderDao;
    private final ITradeLimitService tradeLimitService;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public TradeRouteServiceImpl(TradeOrderDao tradeOrderDao, ITradeLimitService tradeLimitService, IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.tradeOrderDao = tradeOrderDao;
        this.tradeLimitService = tradeLimitService;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

//    @Override
//    public List<TradeRouteMerchantDTO> filterMerchant(TradeRouteDTO tradeRouteDTO) {
//        List<TradeRouteMerchantDTO> tradeRouteMerchants = new ArrayList<>();
//        TradeMerchantQueryDTO query = new TradeMerchantQueryDTO(tradeRouteDTO.getPlatformNumber(),
//                tradeRouteDTO.getChannelNumber(), tradeRouteDTO.getMerchantNumber());
//        List<TradeMerchantDTO> tradeMerchants = payCenterFeignServiceClient.listTradeMerchant(query);
//        if (tradeMerchants != null) {
//            for (TradeMerchantDTO merchantDTO : tradeMerchants) {
//                TradeRouteMerchantDTO tradeRouteMerchantDTO = new TradeRouteMerchantDTO(merchantDTO.getPlatformNumber(),
//                        merchantDTO.getChannelNumber(), merchantDTO.getMerchantNumber());
//
//                tradeRouteMerchants.add(tradeRouteMerchantDTO);
//            }
//        }
//        return tradeRouteMerchants;
//    }
//
//    @Override
//    public void tradeLimit(List<TradeRouteMerchantDTO> tradeRouteMerchants, String defrayalType) {
//        //商户交易限额
//        //1.商户今日、今月交易总额、
//        //2.商户单次、单日、单月交易限额
//        for (TradeRouteMerchantDTO merchantDTO : tradeRouteMerchants) {
//            TradeLimitDO tradeLimitDO = tradeLimitService.selectMerchantTradeLimit(merchantDTO.getMerchantNumber(), defrayalType);
//        }
//
//    }
//
//    @Override
//    public TradeRouteMerchantDTO poll(List<TradeRouteMerchantDTO> tradeRouteMerchants) {
//
//        //todo 轮循
//
//
//        return null;
//    }


    @Override
    public TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO) {
        return null;
    }
}
