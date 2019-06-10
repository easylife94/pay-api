package com.pay.api.core.dao;

import com.pay.api.client.model.TradeSysConfigDO;
import com.pay.common.core.dao.IBaseDao;

/**
 * @author chenwei
 */
public interface TradeSysConfigDao extends IBaseDao<TradeSysConfigDO> {

    /**
     * 根据配置key查询配置项
     *
     * @param configKey
     * @return
     */
    TradeSysConfigDO selectOneByConfigKey(String configKey);
}