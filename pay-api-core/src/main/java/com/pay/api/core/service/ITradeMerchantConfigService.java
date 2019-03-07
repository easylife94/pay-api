package com.pay.api.core.service;

import com.pay.api.client.dto.TradeMerchantConfigDTO;

/**
 * 交易商户配置
 *
 * @author chenwei
 * @date 2019/3/7 15:51
 */
public interface ITradeMerchantConfigService {

    /**
     * 获取商户交易配置
     *
     * @param merchantNumber 商户编号
     * @return 返回商户交易配置
     */
    TradeMerchantConfigDTO getMerchantConfig(String merchantNumber);
}
