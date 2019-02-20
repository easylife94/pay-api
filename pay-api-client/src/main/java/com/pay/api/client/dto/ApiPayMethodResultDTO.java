package com.pay.api.client.dto;

import com.pay.api.client.constants.ApiPayMethodResultEnum;

import java.util.StringJoiner;

/**
 * 支付方法返回结果
 *
 * @author chenwei
 * @date 2019/1/16 11:51
 */
public class ApiPayMethodResultDTO<T> {

    /**
     *
     */
    private ApiPayMethodResultEnum result;

    /**
     * 实际业务代码
     */
    private String subCode;

    /**
     * 实际业务提示信息
     */
    private String subMsg;

    /**
     *
     */
    private T data;

    public ApiPayMethodResultEnum getResult() {
        return result;
    }

    public void setResult(ApiPayMethodResultEnum result) {
        this.result = result;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                ApiPayMethodResultDTO.class.getSimpleName() + "{", "}")
                .add("result=" + result)
                .add("subCode=" + subCode)
                .add("subMsg=" + subMsg)
                .add("data=" + data)
                .toString();
    }
}
