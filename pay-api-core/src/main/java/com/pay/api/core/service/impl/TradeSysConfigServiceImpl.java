package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeSysConfigDTO;
import com.pay.api.client.model.TradeSysConfigDO;
import com.pay.api.core.dao.TradeSysConfigDao;
import com.pay.api.core.service.ITradeSysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/8 10:25
 */
@Service
public class TradeSysConfigServiceImpl implements ITradeSysConfigService {

    private final TradeSysConfigDao tradeSysConfigDao;

    @Autowired
    public TradeSysConfigServiceImpl(TradeSysConfigDao tradeSysConfigDao) {
        this.tradeSysConfigDao = tradeSysConfigDao;
    }

    @Override
    public TradeSysConfigDTO getConfig(String configKey) {
        TradeSysConfigDTO tradeSysConfigDTO = null;
        TradeSysConfigDO tradeSysConfigDO = tradeSysConfigDao.selectOneByConfigKey(configKey);
        if (tradeSysConfigDO != null) {
            tradeSysConfigDTO = new TradeSysConfigDTO(tradeSysConfigDO.getConfigKey(), tradeSysConfigDO.getConfigValue());
        }
        return tradeSysConfigDTO;
    }
}
