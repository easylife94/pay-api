package com.pay.api.client.dto;

import lombok.Data;

/**
 * 支付接口返回参数
 *
 * @author chenwei
 * @date 2019/1/14 11:18
 */
@Data
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
}
