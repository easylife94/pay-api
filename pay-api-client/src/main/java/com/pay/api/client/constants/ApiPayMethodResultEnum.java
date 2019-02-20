package com.pay.api.client.constants;

/**
 * 支付接口方法结果枚举
 *
 * @author chenwei
 * @date 2019/2/20 11:50
 */
public enum ApiPayMethodResultEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * 失败
     */
    FAIL("FAIL", "失败"),
    ;

    private String type;

    private String name;

    ApiPayMethodResultEnum(String type, String name) {
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
    }

}
