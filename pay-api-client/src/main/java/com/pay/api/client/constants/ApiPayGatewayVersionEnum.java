package com.pay.api.client.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 版本枚举
 *
 * @author chenwei
 * @date 2019/1/21 14:43
 */
public enum ApiPayGatewayVersionEnum {

    V_1_0("1.0"),
    ;


    private String version;

    ApiPayGatewayVersionEnum(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取版本枚举
     *
     * @param version 枚举类型
     * @return 无匹配枚举则返回null
     */
    public static ApiPayGatewayVersionEnum get(String version) {
        for (ApiPayGatewayVersionEnum e : ApiPayGatewayVersionEnum.values()) {
            if (StringUtils.equals(version, e.getVersion())) {
                return e;
            }
        }
        return null;
    }
}
