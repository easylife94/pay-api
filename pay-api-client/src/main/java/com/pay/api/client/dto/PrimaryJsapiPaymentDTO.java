package com.pay.api.client.dto;

import lombok.Data;

/**
 * 原生jsapi支付方式参数
 *
 * @author chenwei
 * @date 2019/3/12 18:13
 */
@Data
public class PrimaryJsapiPaymentDTO {

    ////////////////////////////////////支付宝
    /**
     * 支付宝userId
     */
    private String userId;


    ////////////////////////////////////微信
    /**
     * 微信公众号/小程序appId
     */
    private String appId;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 支付宝参数构造函数
     *
     * @param userId 支付宝用户id
     */
    public PrimaryJsapiPaymentDTO(String userId) {
        this.userId = userId;
    }

    /**
     * 微信参数构造函数
     *
     * @param appId  微信公众号/小程序appId
     * @param openId 微信openId
     */
    public PrimaryJsapiPaymentDTO(String appId, String openId) {
        this.appId = appId;
        this.openId = openId;
    }
}
