package com.pay.api.client.dto.rest;

import java.io.Serializable;

/**
 * 交易通道配置创建DTO
 *
 * @author chenwei
 * @date 2019/3/7 16:11
 */
public class TradeChannelConfigCreateFeignDTO implements Serializable {
    private static final long serialVersionUID = 3740238730060536754L;

    private Long channelId;

    private String channelNumber;

    private String channelName;

    private String platformChannelId;

    private String channelSecretKey;

    private String tradeNotifyUrl;

    private String tradeReturnUrl;

    private String registerNotifyUrl;

    private String channelPubKey;

    private String channelPriKey;

    private String platformPubKey;

    private String certUrl;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String alipayPid;

    private String alipayAuthRedirectUrl;

    private String alipayAppId;

    private String alipayPubKey;

    private String alipayAppPubKey;

    private String alipayAppPriKey;

    private String wechatAppId;

    private String wechatAppSecretKey;

    private String wechatAuthRedirectUrl;

    public TradeChannelConfigCreateFeignDTO(Long channelId, String channelNumber, String channelName) {
        this.channelId = channelId;
        this.channelNumber = channelNumber;
        this.channelName = channelName;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPlatformChannelId() {
        return platformChannelId;
    }

    public void setPlatformChannelId(String platformChannelId) {
        this.platformChannelId = platformChannelId;
    }

    public String getChannelSecretKey() {
        return channelSecretKey;
    }

    public void setChannelSecretKey(String channelSecretKey) {
        this.channelSecretKey = channelSecretKey;
    }

    public String getTradeNotifyUrl() {
        return tradeNotifyUrl;
    }

    public void setTradeNotifyUrl(String tradeNotifyUrl) {
        this.tradeNotifyUrl = tradeNotifyUrl;
    }

    public String getTradeReturnUrl() {
        return tradeReturnUrl;
    }

    public void setTradeReturnUrl(String tradeReturnUrl) {
        this.tradeReturnUrl = tradeReturnUrl;
    }

    public String getRegisterNotifyUrl() {
        return registerNotifyUrl;
    }

    public void setRegisterNotifyUrl(String registerNotifyUrl) {
        this.registerNotifyUrl = registerNotifyUrl;
    }

    public String getChannelPubKey() {
        return channelPubKey;
    }

    public void setChannelPubKey(String channelPubKey) {
        this.channelPubKey = channelPubKey;
    }

    public String getChannelPriKey() {
        return channelPriKey;
    }

    public void setChannelPriKey(String channelPriKey) {
        this.channelPriKey = channelPriKey;
    }

    public String getPlatformPubKey() {
        return platformPubKey;
    }

    public void setPlatformPubKey(String platformPubKey) {
        this.platformPubKey = platformPubKey;
    }

    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getAlipayPid() {
        return alipayPid;
    }

    public void setAlipayPid(String alipayPid) {
        this.alipayPid = alipayPid;
    }

    public String getAlipayAuthRedirectUrl() {
        return alipayAuthRedirectUrl;
    }

    public void setAlipayAuthRedirectUrl(String alipayAuthRedirectUrl) {
        this.alipayAuthRedirectUrl = alipayAuthRedirectUrl;
    }

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

    public String getWechatAppSecretKey() {
        return wechatAppSecretKey;
    }

    public void setWechatAppSecretKey(String wechatAppSecretKey) {
        this.wechatAppSecretKey = wechatAppSecretKey;
    }

    public String getWechatAuthRedirectUrl() {
        return wechatAuthRedirectUrl;
    }

    public void setWechatAuthRedirectUrl(String wechatAuthRedirectUrl) {
        this.wechatAuthRedirectUrl = wechatAuthRedirectUrl;
    }

    /**
     * 设置基本配置
     *
     * @param tradeNotifyUrl    交易异步回调地址
     * @param tradeReturnUrl    交易通道回调地址
     * @param registerNotifyUrl 进件异步回调地址
     */
    public void setBasicConfig(String tradeNotifyUrl, String tradeReturnUrl, String registerNotifyUrl) {
        this.tradeNotifyUrl = tradeNotifyUrl;
        this.tradeReturnUrl = tradeReturnUrl;
        this.registerNotifyUrl = registerNotifyUrl;
    }

    /**
     * 设置平台配置
     *
     * @param platformChannelId 平台通道编号
     * @param platformPubKey    平台公钥
     * @param channelSecretKey  平台通道密钥
     * @param certUrl           证书路径
     * @param channelPubKey     平台通道公钥
     * @param channelPriKey     平台通道私钥
     */
    public void setPlatformConfig(String platformChannelId, String platformPubKey, String channelSecretKey, String certUrl, String channelPubKey,
                                  String channelPriKey) {
        this.platformChannelId = platformChannelId;
        this.platformPubKey = platformPubKey;
        this.channelSecretKey = channelSecretKey;
        this.certUrl = certUrl;
        this.channelPubKey = channelPubKey;
        this.channelPriKey = channelPriKey;
    }

    /**
     * 设置支付宝配置
     *
     * @param alipayPid             支付宝服务商pid
     * @param alipayAppId           支付宝应用appId
     * @param alipayPubKey          支付宝应用的平台公钥
     * @param alipayAppPubKey       支付宝应用的公钥
     * @param alipayAppPriKey       支付宝应用的私钥
     * @param alipayAuthRedirectUrl 支付宝授权回调地址
     */
    public void setAliConfig(String alipayPid, String alipayAppId, String alipayPubKey, String alipayAppPubKey, String alipayAppPriKey, String alipayAuthRedirectUrl) {
        this.alipayPid = alipayPid;
        this.alipayAppId = alipayAppId;
        this.alipayPubKey = alipayPubKey;
        this.alipayAppPubKey = alipayAppPubKey;
        this.alipayAppPriKey = alipayAppPriKey;
        this.alipayAuthRedirectUrl = alipayAuthRedirectUrl;
    }

    /**
     * 设置微信配置
     *
     * @param wechatAppId
     * @param wechatAppSecretKey
     * @param wechatAuthRedirectUrl
     */
    public void setWechatConfig(String wechatAppId, String wechatAppSecretKey, String wechatAuthRedirectUrl) {
        this.wechatAppId = wechatAppId;
        this.wechatAppSecretKey = wechatAppSecretKey;
        this.wechatAuthRedirectUrl = wechatAuthRedirectUrl;
    }

    /**
     * 设置拓展配置
     *
     * @param ext1 拓展配置1
     * @param ext2 拓展配置2
     * @param ext3 拓展配置3
     * @param ext4 拓展配置4
     */
    public void setExtConfig(String ext1, String ext2, String ext3, String ext4) {
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
        this.ext4 = ext4;
    }
}
