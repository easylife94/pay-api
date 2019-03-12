package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMerchantConfigDO;

public interface TradeMerchantConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeMerchantConfigDO record);

    int insertSelective(TradeMerchantConfigDO record);

    TradeMerchantConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeMerchantConfigDO record);

    int updateByPrimaryKey(TradeMerchantConfigDO record);

    /**
     * 根据商户编号获取交易商户配置
     *
     * @param merchantNumber 商户编号
     * @return 返回交易商户配置
     */
    TradeMerchantConfigDO selectOneByMerchantNumber(String merchantNumber);
}