package com.pay.api.client.model;

import java.math.BigDecimal;
import java.util.Date;

public class TradeRouteDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private String memberNumber;

    private String platformMapped;

    private String platformNumber;

    private String channelNumber;

    private Long merchantId;

    private String merchantNumber;

    private String defrayalChannel;

    private String defrayalType;

    private String merchantName;

    private Boolean status;

    private BigDecimal singleTradeAmountMin;

    private BigDecimal singleTradeAmountMax;

    private Boolean tradeLimit;

    private Date tradeLimitTime;

    private Boolean tradeRisk;

    private Date tradeRiskTime;

    private Long tradeIntervalTime;

    private Long lastTradeTimestamp;

    private Long lastTradeSucTimestamp;

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

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber == null ? null : memberNumber.trim();
    }

    public String getPlatformMapped() {
        return platformMapped;
    }

    public void setPlatformMapped(String platformMapped) {
        this.platformMapped = platformMapped;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber == null ? null : platformNumber.trim();
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber == null ? null : channelNumber.trim();
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
        this.merchantNumber = merchantNumber == null ? null : merchantNumber.trim();
    }

    public String getDefrayalChannel() {
        return defrayalChannel;
    }

    public void setDefrayalChannel(String defrayalChannel) {
        this.defrayalChannel = defrayalChannel == null ? null : defrayalChannel.trim();
    }

    public String getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(String defrayalType) {
        this.defrayalType = defrayalType == null ? null : defrayalType.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Boolean getTradeLimit() {
        return tradeLimit;
    }

    public void setTradeLimit(Boolean tradeLimit) {
        this.tradeLimit = tradeLimit;
    }

    public Date getTradeLimitTime() {
        return tradeLimitTime;
    }

    public void setTradeLimitTime(Date tradeLimitTime) {
        this.tradeLimitTime = tradeLimitTime;
    }

    public Boolean getTradeRisk() {
        return tradeRisk;
    }

    public void setTradeRisk(Boolean tradeRisk) {
        this.tradeRisk = tradeRisk;
    }

    public Date getTradeRiskTime() {
        return tradeRiskTime;
    }

    public void setTradeRiskTime(Date tradeRiskTime) {
        this.tradeRiskTime = tradeRiskTime;
    }

    public Long getTradeIntervalTime() {
        return tradeIntervalTime;
    }

    public void setTradeIntervalTime(Long tradeIntervalTime) {
        this.tradeIntervalTime = tradeIntervalTime;
    }

    public Long getLastTradeTimestamp() {
        return lastTradeTimestamp;
    }

    public void setLastTradeTimestamp(Long lastTradeTimestamp) {
        this.lastTradeTimestamp = lastTradeTimestamp;
    }

    public Long getLastTradeSucTimestamp() {
        return lastTradeSucTimestamp;
    }

    public void setLastTradeSucTimestamp(Long lastTradeSucTimestamp) {
        this.lastTradeSucTimestamp = lastTradeSucTimestamp;
    }
}