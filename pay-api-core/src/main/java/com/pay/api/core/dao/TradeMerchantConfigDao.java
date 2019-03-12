package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMerchantConfigDO;

public interface TradeMerchantConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeMerchantConfigDO record);

    int insertSelective(TradeMerchantConfigDO record);

    TradeMerchantConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeMerchantConfigDO record);

    int updateByPrimaryKey(TradeMerchantConfigDO record);
}