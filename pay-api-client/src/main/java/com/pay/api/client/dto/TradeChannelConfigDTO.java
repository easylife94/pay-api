package com.pay.api.client.dto;

import lombok.Data;

/**
 * 交易通道配置
 *
 * @author chenwei
 * @date 2019/3/7 15:25
 */
@Data
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
}
