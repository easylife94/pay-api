package com.pay.api.core.service;

import com.pay.api.client.dto.TradeMerchantRiskControlDTO;

/**
 * 交易风控服务接口
 *
 * @author chenwei
 * @date 2019/2/1 11:35
 */
public interface ITradeRiskControlService {


    /**
     * 直接风控商户
     *
     * @param tradeMerchantRiskControlDTO
     */
    void merchantRiskControl(TradeMerchantRiskControlDTO tradeMerchantRiskControlDTO);

    /**
     * 商户加入风控预警
     *
     * @param tradeMerchantRiskControlDTO
     */
    void merchantRiskControlWarn(TradeMerchantRiskControlDTO tradeMerchantRiskControlDTO);
}
