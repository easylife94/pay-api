package com.pay.api.client.constants;

/**
 * 支付网关接口结果枚举
 *
 * @author chenwei
 * @date 2019/1/16 11:20
 */
public enum ApiPayGatewayResultEnum {

    /**
     *
     */
    SUCCESS("10000", "成功"),
    SIGN_ERROR("20000", "签名错误"),
    METHOD_NOT_EXIST("30000", "方法不存在"),
    ;

    private String code;
    private String msg;

    ApiPayGatewayResultEnum(String code, String msg) {
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
