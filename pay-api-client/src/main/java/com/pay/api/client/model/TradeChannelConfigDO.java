package com.pay.api.client.model;

import java.util.Date;

public class TradeChannelConfigDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
        this.channelNumber = channelNumber == null ? null : channelNumber.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getPlatformChannelId() {
        return platformChannelId;
    }

    public void setPlatformChannelId(String platformChannelId) {
        this.platformChannelId = platformChannelId == null ? null : platformChannelId.trim();
    }

    public String getChannelSecretKey() {
        return channelSecretKey;
    }

    public void setChannelSecretKey(String channelSecretKey) {
        this.channelSecretKey = channelSecretKey == null ? null : channelSecretKey.trim();
    }

    public String getTradeNotifyUrl() {
        return tradeNotifyUrl;
    }

    public void setTradeNotifyUrl(String tradeNotifyUrl) {
        this.tradeNotifyUrl = tradeNotifyUrl == null ? null : tradeNotifyUrl.trim();
    }

    public String getTradeReturnUrl() {
        return tradeReturnUrl;
    }

    public void setTradeReturnUrl(String tradeReturnUrl) {
        this.tradeReturnUrl = tradeReturnUrl == null ? null : tradeReturnUrl.trim();
    }

    public String getRegisterNotifyUrl() {
        return registerNotifyUrl;
    }

    public void setRegisterNotifyUrl(String registerNotifyUrl) {
        this.registerNotifyUrl = registerNotifyUrl == null ? null : registerNotifyUrl.trim();
    }

    public String getChannelPubKey() {
        return channelPubKey;
    }

    public void setChannelPubKey(String channelPubKey) {
        this.channelPubKey = channelPubKey == null ? null : channelPubKey.trim();
    }

    public String getChannelPriKey() {
        return channelPriKey;
    }

    public void setChannelPriKey(String channelPriKey) {
        this.channelPriKey = channelPriKey == null ? null : channelPriKey.trim();
    }

    public String getPlatformPubKey() {
        return platformPubKey;
    }

    public void setPlatformPubKey(String platformPubKey) {
        this.platformPubKey = platformPubKey == null ? null : platformPubKey.trim();
    }

    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl == null ? null : certUrl.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getAlipayPid() {
        return alipayPid;
    }

    public void setAlipayPid(String alipayPid) {
        this.alipayPid = alipayPid == null ? null : alipayPid.trim();
    }

    public String getAlipayAuthRedirectUrl() {
        return alipayAuthRedirectUrl;
    }

    public void setAlipayAuthRedirectUrl(String alipayAuthRedirectUrl) {
        this.alipayAuthRedirectUrl = alipayAuthRedirectUrl == null ? null : alipayAuthRedirectUrl.trim();
    }

    public String getAlipayAppId() {
        return alipayAppId;
    }

    public void setAlipayAppId(String alipayAppId) {
        this.alipayAppId = alipayAppId == null ? null : alipayAppId.trim();
    }

    public String getAlipayPubKey() {
        return alipayPubKey;
    }

    public void setAlipayPubKey(String alipayPubKey) {
        this.alipayPubKey = alipayPubKey == null ? null : alipayPubKey.trim();
    }

    public String getAlipayAppPubKey() {
        return alipayAppPubKey;
    }

    public void setAlipayAppPubKey(String alipayAppPubKey) {
        this.alipayAppPubKey = alipayAppPubKey == null ? null : alipayAppPubKey.trim();
    }

    public String getAlipayAppPriKey() {
        return alipayAppPriKey;
    }

    public void setAlipayAppPriKey(String alipayAppPriKey) {
        this.alipayAppPriKey = alipayAppPriKey == null ? null : alipayAppPriKey.trim();
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId == null ? null : wechatAppId.trim();
    }

    public String getWechatAppSecretKey() {
        return wechatAppSecretKey;
    }

    public void setWechatAppSecretKey(String wechatAppSecretKey) {
        this.wechatAppSecretKey = wechatAppSecretKey == null ? null : wechatAppSecretKey.trim();
    }

    public String getWechatAuthRedirectUrl() {
        return wechatAuthRedirectUrl;
    }

    public void setWechatAuthRedirectUrl(String wechatAuthRedirectUrl) {
        this.wechatAuthRedirectUrl = wechatAuthRedirectUrl == null ? null : wechatAuthRedirectUrl.trim();
    }
}