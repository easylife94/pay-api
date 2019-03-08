package com.pay.api.core.service;

import com.pay.api.client.dto.TradeSysConfigDTO;

/**
 * 交易系统配置服务接口
 *
 * @author chenwei
 * @date 2019/3/8 10:24
 */
public interface ITradeSysConfigService {

    /**
     * 根据配置key获取配置value
     *
     * @param configKey 配置key
     * @return
     */
    TradeSysConfigDTO getConfig(String configKey);

}
