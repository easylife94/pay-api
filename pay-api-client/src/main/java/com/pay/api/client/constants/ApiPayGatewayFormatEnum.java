package com.pay.api.client.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 格式枚举
 *
 * @author chenwei
 * @date 2019/1/21 14:38
 */
public enum ApiPayGatewayFormatEnum {

    /**
     * JSON
     */
    JSON("JSON", "JSON"),
    ;

    private String type;
    private String name;

    ApiPayGatewayFormatEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据type获取枚举
     *
     * @param type 枚举类型
     * @return 无匹配枚举则返回null
     */
    public static ApiPayGatewayFormatEnum getByType(String type) {
        for (ApiPayGatewayFormatEnum e : ApiPayGatewayFormatEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e;
            }
        }
        return null;
    }
}
