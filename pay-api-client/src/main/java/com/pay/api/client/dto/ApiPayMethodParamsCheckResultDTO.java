package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 支付方法参数校验返回结果
 *
 * @author chenwei
 * @date 2019/2/1 11:49
 */
public class ApiPayMethodParamsCheckResultDTO<T> extends ApiPayParamsCheckResultDTO {

    /**
     * 转化后参数
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                super.toString() +
                        ApiPayMethodParamsCheckResultDTO.class.getSimpleName() + "{", "}")
                .add("data=" + data)
                .toString();
    }
}
