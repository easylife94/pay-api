package com.pay.api.client.dto.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * 交易通道配置创建DTO
 *
 * @author chenwei
 * @date 2019/3/7 16:11
 */
@Data
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
