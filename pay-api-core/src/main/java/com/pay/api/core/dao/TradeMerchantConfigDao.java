package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMerchantConfigDO;
import com.pay.common.core.dao.IBaseDao;

/**
 * @author chenwei
 */
public interface TradeMerchantConfigDao extends IBaseDao<TradeMerchantConfigDO> {

    /**
     * 根据商户编号获取交易商户配置
     *
     * @param merchantNumber 商户编号
     * @return 返回交易商户配置
     */
    TradeMerchantConfigDO selectOneByMerchantNumber(String merchantNumber);
}