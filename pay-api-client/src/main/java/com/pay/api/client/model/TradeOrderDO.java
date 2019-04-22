package com.pay.api.client.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeOrderDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private String sysOrderNumber;

    private String memberOrderNumber;

    private String platformOrderNumber;

    private String sourceOrderNumber;

    private String tradeType;

    private Date sysOrderTime;

    private Date platformOrderTime;

    private Date payTime;

    private Date sysCheckTime;

    private Date platformCheckTime;

    private Date sysNotifyTime;

    private Date platformNotifyTime;

    private String tradeStatus;

    private String notifyStatus;

    private String checkStatus;

    private String defrayalChannel;

    private String defrayalType;

    private Long memberId;

    private String memberNumber;

    private String memberName;

    private Long agentId;

    private String agentNumber;

    private String agentName;

    private String agentLevel;

    private Long platformId;

    private String platformMapped;

    private String platformNumber;

    private String platformName;

    private Long channelId;

    private String channelNumber;

    private String channelName;

    private Long merchantId;

    private String merchantNumber;

    private String merchantName;

    private BigDecimal tradeAmount;

    private BigDecimal payAmount;

    private BigDecimal privilegeAmount;

    private BigDecimal refundAmount;

    private BigDecimal checkAmount;

    private BigDecimal channelFee;

    private BigDecimal channelPlatformFee;

    private BigDecimal channelProfit;

    private BigDecimal sysChannelProfit;

    private BigDecimal agentChannelProfit;

    private BigDecimal serviceFee;

    private BigDecimal sysServiceProfit;

    private BigDecimal agentServiceProfit;

    private String currency;

    private String closeCause;

    private String closeType;

    private String title;

    private String body;

    private String attach;

    private String payContent;
}