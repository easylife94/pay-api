package com.pay.api.client.dto.api;

/**
 * 支付接口参数校验结果DTO
 *
 * @author chenwei
 * @date 2019/1/21 11:53
 */
public class ApiPayParamsCheckResultDTO {

    /**
     * 校验通过，当且仅当校验通过时为true
     */
    private Boolean pass;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 错误信息
     */
    private String msg;

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
