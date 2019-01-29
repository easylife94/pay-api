package com.pay.api.core.dao;

import com.pay.api.client.model.TradeLimitDO;

public interface TradeLimitDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeLimitDO record);

    int insertSelective(TradeLimitDO record);

    TradeLimitDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeLimitDO record);

    int updateByPrimaryKey(TradeLimitDO record);
}