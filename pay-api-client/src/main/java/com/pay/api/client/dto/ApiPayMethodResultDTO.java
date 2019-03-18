package com.pay.api.client.dto;

import com.pay.api.client.constants.ApiPayMethodResultEnum;
import lombok.Data;

import java.util.StringJoiner;

/**
 * 支付方法返回结果
 *
 * @author chenwei
 * @date 2019/1/16 11:51
 */
@Data
public class ApiPayMethodResultDTO<T> {

    /**
     * 支付方法执行结果
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
     * 业务数据
     */
    private T data;

}
