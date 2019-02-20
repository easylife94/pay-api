package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeMerchantRiskControlDTO;
import com.pay.api.core.service.ITradeRiskControlService;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/2/1 11:36
 */
@Service
public class TradeRiskControlServiceImpl implements ITradeRiskControlService {

    @Override
    public void merchantRiskControl(TradeMerchantRiskControlDTO tradeMerchantRiskControlDTO) {
        //TODO 商户风控
    }

    @Override
    public void merchantRiskControlWarn(TradeMerchantRiskControlDTO tradeMerchantRiskControlDTO) {
        //TODO 商户风控预警
    }
}
