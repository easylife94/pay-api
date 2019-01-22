package com.pay.api.client.constants;

/**
 * 支付网关公共参数错误枚举
 *
 * @author chenwei
 * @date 2019-01-20
 */
public enum ApiPayGatewayPublicParamsErrorEnum {

    /**
     * 参数必填
     */
    PARAM_REQUIRED("PARAM_REQUIRED", "参数必填"),

    /**
     * 签名类型错误
     */
    SIGN_TYPE_ERROR("SIGN_TYPE_ERROR", "签名类型错误"),

    /**
     * 格式错误
     */
    FORMAT_ERROR("FORMAT_ERROR", "格式错误"),

    /**
     * 版本错误
     */
    VERSION_ERROR("VERSION_ERROR", "版本错误");

    /**
     * 类型
     */
    private String type;

    /**
     * 错误信息
     */
    private String error;

    ApiPayGatewayPublicParamsErrorEnum(String type, String error) {
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
