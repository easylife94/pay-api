package com.pay.api.client.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 签名类型枚举
 *
 * @author chenwei
 * @date 2019/1/21 11:14
 */
public enum ApiPayGatewaySignTypeEnum {

    /**
     * RSA签名算法
     */
    RSA("RSA", "RSA"),

    /**
     * RSA2签名算法
     */
    RSA2("RSA2", "RSA2");

    private String type;
    private String name;

    ApiPayGatewaySignTypeEnum(String type, String name) {
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
    public static ApiPayGatewaySignTypeEnum getByType(String type) {
        for (ApiPayGatewaySignTypeEnum e : ApiPayGatewaySignTypeEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e;
            }
        }
        return null;
    }

}
