package com.pay.api.core.service;

import com.pay.api.client.model.TradeLimitDO;

/**
 * @author chenwei
 * @date 2019/2/15 16:05
 */
public interface ITradeLimitService {

    /**
     * 查询商户交易限额
     *
     * @param merchantNumber
     * @param defrayalType
     * @return
     */
    TradeLimitDO selectMerchantTradeLimit(String merchantNumber, String defrayalType);
}
