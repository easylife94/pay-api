package com.pay.api.core.dao;

import com.pay.api.client.model.TradeLimitDO;

public interface TradeLimitDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeLimitDO record);

    int insertSelective(TradeLimitDO record);

    TradeLimitDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeLimitDO record);

    int updateByPrimaryKey(TradeLimitDO record);

    /**
     * 根据拥有者查询
     *
     * @param ownType   拥有者类型
     * @param ownNumber 拥有者编号
     * @param defrayalType 支付方式
     * @return
     */
    TradeLimitDO selectByOwn(String ownType, String ownNumber,String defrayalType);

}