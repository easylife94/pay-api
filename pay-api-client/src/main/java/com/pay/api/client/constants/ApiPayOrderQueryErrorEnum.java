package com.pay.api.client.constants;

/**
 * 订单查询错误枚举
 *
 * @author chenwei
 * @date 2019/2/20 11:27
 */
public enum ApiPayOrderQueryErrorEnum {

    /**
     * 订单不存在
     */
    ORDER_NOT_EXIST("ORDER_NOT_EXIST","订单不存在"),
    ;

    private String code;
    private String msg;

    ApiPayOrderQueryErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
