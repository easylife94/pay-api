package com.pay.api.client.constants;

/**
 * 支付网关接口结果枚举
 *
 * @author chenwei
 * @date 2019/1/16 11:20
 */
public enum ApiPayGatewayResultEnum {

    /**
     * 成功
     */
    SUCCESS("10000", "成功"),

    /**
     * 会员不存在
     */
    MEMBER_NOT_EXIST("20000", "会员不存在"),

    /**
     * 验证签名错误
     */
    VERIFY_SIGN_ERROR("30000", "验证签名错误"),

    /**
     * 方法不存在
     */
    METHOD_NOT_EXIST("40000", "方法不存在"),

    /**
     * 业务处理失败
     */
    BUSINESS_FAIL("40001", "业务处理失败"),

    /**
     * 公共参数错误
     */
    PUBLIC_PARAMS_ERROR("50000", "公共参数错误"),

    /**
     * 签名错误
     */
    SIGN_ERROR("60000", "签名错误"),
    //add new enum above

    /**
     * 系统异常
     */
    SYSTEM_ERROR("90000", "系统异常"),
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

    public String getMsg() {
        return msg;
    }
}
