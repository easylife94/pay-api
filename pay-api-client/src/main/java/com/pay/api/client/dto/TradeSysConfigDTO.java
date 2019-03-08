package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/3/8 10:23
 */
public class TradeSysConfigDTO {

    private String configKey;

    private String configValue;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public TradeSysConfigDTO(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeSysConfigDTO.class.getSimpleName() + "{", "}")
                .add("configKey=" + configKey)
                .add("configValue=" + configValue)
                .toString();
    }
}
