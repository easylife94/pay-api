package com.pay.api.client.dto;

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
}
