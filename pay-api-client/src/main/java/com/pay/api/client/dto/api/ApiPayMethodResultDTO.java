package com.pay.api.client.dto.api;

import java.util.StringJoiner;

/**
 * 支付方法返回结果
 *
 * @author chenwei
 * @date 2019/1/16 11:51
 */
public class ApiPayMethodResultDTO<T> {

    private String subCode;
    private String subMsg;
    private T data;

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
        return new StringJoiner(", ", ApiPayMethodResultDTO.class.getSimpleName() + "[", "]")
                .add("subCode='" + subCode + "'")
                .add("subMsg='" + subMsg + "'")
                .add("data=" + data)
                .toString();
    }
}
