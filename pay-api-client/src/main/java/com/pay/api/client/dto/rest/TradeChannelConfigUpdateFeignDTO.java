package com.pay.api.client.dto.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenwei
 * @date 2019/3/7 16:15
 */
@Data
public class TradeChannelConfigUpdateFeignDTO implements Serializable {

    private String channelNumber;

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
}
