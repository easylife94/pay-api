package com.pay.api.client.dto;

import lombok.Data;

import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/3/8 10:23
 */
@Data
public class TradeSysConfigDTO {

    private String configKey;

    private String configValue;

    public TradeSysConfigDTO(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }
}
