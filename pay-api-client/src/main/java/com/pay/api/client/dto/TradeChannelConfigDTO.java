package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易通道配置
 *
 * @author chenwei
 * @date 2019/3/7 15:25
 */
public class TradeChannelConfigDTO {

    /**
     * 支付宝应用appId
     */
    private String alipayAppId;

    /**
     * 支付宝公钥
     */
    private String alipayPubKey;

    /**
     * 支付宝应用公钥
     */
    private String alipayAppPubKey;

    /**
     * 支付宝应用私钥
     */
    private String alipayAppPriKey;


    /**
     * 微信公众号appid
     */
    private String wechatAppId;

    /**
     * 微信公众号密钥
     */
    private String wechatSecretKey;

    public String getAlipayAppId() {
        return alipayAppId;
    }

    public void setAlipayAppId(String alipayAppId) {
        this.alipayAppId = alipayAppId;
    }

    public String getAlipayPubKey() {
        return alipayPubKey;
    }

    public void setAlipayPubKey(String alipayPubKey) {
        this.alipayPubKey = alipayPubKey;
    }

    public String getAlipayAppPubKey() {
        return alipayAppPubKey;
    }

    public void setAlipayAppPubKey(String alipayAppPubKey) {
        this.alipayAppPubKey = alipayAppPubKey;
    }

    public String getAlipayAppPriKey() {
        return alipayAppPriKey;
    }

    public void setAlipayAppPriKey(String alipayAppPriKey) {
        this.alipayAppPriKey = alipayAppPriKey;
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId;
    }

    public String getWechatSecretKey() {
        return wechatSecretKey;
    }

    public void setWechatSecretKey(String wechatSecretKey) {
        this.wechatSecretKey = wechatSecretKey;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeChannelConfigDTO.class.getSimpleName() + "{", "}")
                .add("alipayAppId=" + alipayAppId)
                .add("alipayPubKey=" + alipayPubKey)
                .add("alipayAppPubKey=" + alipayAppPubKey)
                .add("alipayAppPriKey=" + alipayAppPriKey)
                .add("wechatAppId=" + wechatAppId)
                .add("wechatSecretKey=" + wechatSecretKey)
                .toString();
    }
}
