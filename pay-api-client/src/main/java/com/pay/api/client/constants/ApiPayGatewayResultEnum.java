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
    MEMBER_NOT_EXIST("20000", "会员不存在"),
    SIGN_ERROR("30000", "签名错误"),
    METHOD_NOT_EXIST("40000", "方法不存在"),
    PUBLIC_PARAMS_ERROR("50000", "公共参数错误");;

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
