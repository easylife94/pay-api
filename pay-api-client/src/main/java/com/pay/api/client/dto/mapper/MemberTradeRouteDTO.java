package com.pay.api.client.dto.mapper;

import java.math.BigDecimal;

/**
 * 会员交易路由
 *
 * @author chenwei
 * @date 2019/3/14 17:41
 */
public class MemberTradeRouteDTO {
    private Long id;
    private String platformMapped;
    private Long platformId;
    private String platformNumber;
    private String platformName;
    private Long channelId;
    private String channelNumber;
    private String channelName;
    private Long merchantId;
    private String merchantNumber;
    private String merchantName;
    private Long lastTradeTimestamp;
    private BigDecimal singleTradeAmountMin;
    private BigDecimal singleTradeAmountMax;
    private Long tradeIntervalTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformMapped() {
        return platformMapped;
    }

    public void setPlatformMapped(String platformMapped) {
        this.platformMapped = platformMapped;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getLastTradeTimestamp() {
        return lastTradeTimestamp;
    }

    public void setLastTradeTimestamp(Long lastTradeTimestamp) {
        this.lastTradeTimestamp = lastTradeTimestamp;
    }

    public BigDecimal getSingleTradeAmountMin() {
        return singleTradeAmountMin;
    }

    public void setSingleTradeAmountMin(BigDecimal singleTradeAmountMin) {
        this.singleTradeAmountMin = singleTradeAmountMin;
    }

    public BigDecimal getSingleTradeAmountMax() {
        return singleTradeAmountMax;
    }

    public void setSingleTradeAmountMax(BigDecimal singleTradeAmountMax) {
        this.singleTradeAmountMax = singleTradeAmountMax;
    }

    public Long getTradeIntervalTime() {
        return tradeIntervalTime;
    }

    public void setTradeIntervalTime(Long tradeIntervalTime) {
        this.tradeIntervalTime = tradeIntervalTime;
    }
}
