package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 支付接口返回参数
 *
 * @author chenwei
 * @date 2019/1/14 11:18
 */
public class ApiPayResultDTO {

    /**
     * 支付网关返回码
     */
    private String code;

    /**
     * 支付网关返回信息
     */
    private String msg;

    /**
     * 接口返回码
     */
    private String subCode;

    /**
     * 接口返回信息
     */
    private String subMsg;

    /**
     * 签名串，在网关返回成功的时候返回
     */
    private String sign;

    /**
     * 返回参数内容
     */
    private Object content;

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

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiPayResultDTO.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("msg='" + msg + "'")
                .add("subCode='" + subCode + "'")
                .add("subMsg='" + subMsg + "'")
                .add("sign='" + sign + "'")
                .add("content=" + content)
                .toString();
    }
}
