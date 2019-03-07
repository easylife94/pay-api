package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.core.service.ITradeMerchantConfigService;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/7 15:51
 */
@Service
public class TradeMerchantConfigServiceImpl implements ITradeMerchantConfigService {

    @Override
    public TradeMerchantConfigDTO getMerchantConfig(String merchantNumber) {
        //todo  获取商户交易配置
        return null;
    }
}
