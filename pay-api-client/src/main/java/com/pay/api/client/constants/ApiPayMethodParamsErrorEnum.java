package com.pay.api.client.constants;

/**
 * 支付接口方法参数错误枚举类
 *
 * @author chenwei
 * @date 2019/2/1 13:54
 */
public enum ApiPayMethodParamsErrorEnum {

    /**
     * 参数必填
     */
    REQUIRED("REQUIRED","必填参数"),

    /**
     * 参数格式错误
     */
    FORMAT_ERROR("FORMAT_ERROR","参数格式错误"),

    /**
     * 参数值无效
     */
    INVALID("INVALID","参数值无效"),



    ;


    private String type;
    private String name;

    ApiPayMethodParamsErrorEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
