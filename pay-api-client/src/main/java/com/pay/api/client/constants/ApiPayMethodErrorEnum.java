package com.pay.api.client.constants;

/**
 * 方法执行错误枚举
 *
 * @author chenwei
 * @date 2019-02-20
 */
public enum ApiPayMethodErrorEnum {

    /**
     * 校验参数失败
     */
    CHECK_FAIL("CHECK_FAIL", "校验参数失败"),

    /**
     * 方法执行过程抛异常
     */
    OPERATE_EXCEPTION("OPERATE_EXCEPTION", "方法执行异常"),

    ;

    private String code;
    private String msg;

    ApiPayMethodErrorEnum(String code, String msg) {
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
