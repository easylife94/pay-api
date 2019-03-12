package com.pay.api.core.dao;

import com.pay.api.client.model.TradeSysConfigDO;

public interface TradeSysConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeSysConfigDO record);

    int insertSelective(TradeSysConfigDO record);

    TradeSysConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeSysConfigDO record);

    int updateByPrimaryKey(TradeSysConfigDO record);

    /**
     * 根据配置key查询配置项
     *
     * @param configKey
     * @return
     */
    TradeSysConfigDO selectOneByConfigKey(String configKey);
}