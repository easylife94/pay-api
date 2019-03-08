package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeSysConfigDTO;
import com.pay.api.core.service.ITradeSysConfigService;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/8 10:25
 */
@Service
public class TradeSysConfigServiceImpl implements ITradeSysConfigService {

    @Override
    public TradeSysConfigDTO getConfig(String configKey) {
        //todo 等待新增model和dao
        return null;
    }
}
