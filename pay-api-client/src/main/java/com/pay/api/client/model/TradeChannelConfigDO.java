package com.pay.api.client.model;

import lombok.Data;

import java.util.Date;

/**
 * @author chenwei
 */
@Data
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

    private String checkDay;

    private String checkMethod;

    private Integer checkTimeHour;

    private Integer checkTimeMin;
}