package com.pay.api.client.constants;

/**
 * 支付网关公共参数错误枚举
 *
 * @author chenwei
 * @date 2019-01-20
 */
public enum ApiPayGatewayParamsErrorEnum {

    //todo 支付网关公共参数错误枚举
    ;
    /**
     * 类型
     */
    private String type;

    /**
     * 错误信息
     */
    private String error;

    ApiPayGatewayParamsErrorEnum(String type, String error) {
        this.type = type;
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
