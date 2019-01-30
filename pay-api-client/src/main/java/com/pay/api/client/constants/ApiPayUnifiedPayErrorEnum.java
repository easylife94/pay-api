package com.pay.api.client.constants;

/**
 * 支付接口 - 统一支付错误枚举
 *
 * @author chenwei
 * @date 2019-01-29
 */
public enum ApiPayUnifiedPayErrorEnum {

    /**
     * 无可用商户
     */
    MERCHANT_DISABLE("MERCHANT_DISABLE","无可用商户"),
    ;

    private String type;
    private String error;

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

    ApiPayUnifiedPayErrorEnum(String type, String error) {
        this.type = type;
        this.error = error;
    }
}
