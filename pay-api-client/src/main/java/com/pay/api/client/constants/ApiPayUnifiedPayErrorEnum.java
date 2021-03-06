package com.pay.api.client.constants;

/**
 * 支付接口 - 统一支付错误枚举
 *
 * @author chenwei
 * @date 2019-01-29
 */
public enum ApiPayUnifiedPayErrorEnum {

    /**
     * 无商户交易路由
     */
    NONE_MERCHANT_ROUTE("NONE_MERCHANT_ROUTE", "无商户交易路由"),
    ;

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ApiPayUnifiedPayErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
