package com.pay.api.client.dto;

/**
 * 原生jsapi支付方式参数
 *
 * @author chenwei
 * @date 2019/3/12 18:13
 */
public class PrimaryJsapiPaymentDTO {

    ////////////////////////////////////支付宝
    /**
     * 支付宝userId
     */
    private String userId;


    ////////////////////////////////////微信
    private String appId;
    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 支付宝参数构造函数
     * @param userId
     */
    public PrimaryJsapiPaymentDTO(String userId) {
        this.userId = userId;
    }

    /**
     * 微信参数构造函数
     * @param appId
     * @param openId
     */
    public PrimaryJsapiPaymentDTO(String appId, String openId) {
        this.appId = appId;
        this.openId = openId;
    }
}
