package com.pay.api.client.model;

import java.math.BigDecimal;
import java.util.Date;

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

    private Long sysOrderTime;

    private Long platformOrderTime;

    private Long payTime;

    private Long sysCheckTime;

    private Long platformCheckTime;

    private Long sysNotifyTime;

    private Long platformNotifyTime;

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

    private String payContent;

    private String closeCause;

    private String closeType;

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

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber == null ? null : sysOrderNumber.trim();
    }

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber == null ? null : memberOrderNumber.trim();
    }

    public String getPlatformOrderNumber() {
        return platformOrderNumber;
    }

    public void setPlatformOrderNumber(String platformOrderNumber) {
        this.platformOrderNumber = platformOrderNumber == null ? null : platformOrderNumber.trim();
    }

    public String getSourceOrderNumber() {
        return sourceOrderNumber;
    }

    public void setSourceOrderNumber(String sourceOrderNumber) {
        this.sourceOrderNumber = sourceOrderNumber == null ? null : sourceOrderNumber.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public Long getSysOrderTime() {
        return sysOrderTime;
    }

    public void setSysOrderTime(Long sysOrderTime) {
        this.sysOrderTime = sysOrderTime;
    }

    public Long getPlatformOrderTime() {
        return platformOrderTime;
    }

    public void setPlatformOrderTime(Long platformOrderTime) {
        this.platformOrderTime = platformOrderTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getSysCheckTime() {
        return sysCheckTime;
    }

    public void setSysCheckTime(Long sysCheckTime) {
        this.sysCheckTime = sysCheckTime;
    }

    public Long getPlatformCheckTime() {
        return platformCheckTime;
    }

    public void setPlatformCheckTime(Long platformCheckTime) {
        this.platformCheckTime = platformCheckTime;
    }

    public Long getSysNotifyTime() {
        return sysNotifyTime;
    }

    public void setSysNotifyTime(Long sysNotifyTime) {
        this.sysNotifyTime = sysNotifyTime;
    }

    public Long getPlatformNotifyTime() {
        return platformNotifyTime;
    }

    public void setPlatformNotifyTime(Long platformNotifyTime) {
        this.platformNotifyTime = platformNotifyTime;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus == null ? null : notifyStatus.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber == null ? null : memberNumber.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber == null ? null : agentNumber.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
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
        this.platformNumber = platformNumber == null ? null : platformNumber.trim();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName == null ? null : platformName.trim();
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPrivilegeAmount() {
        return privilegeAmount;
    }

    public void setPrivilegeAmount(BigDecimal privilegeAmount) {
        this.privilegeAmount = privilegeAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public BigDecimal getChannelFee() {
        return channelFee;
    }

    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    public BigDecimal getChannelPlatformFee() {
        return channelPlatformFee;
    }

    public void setChannelPlatformFee(BigDecimal channelPlatformFee) {
        this.channelPlatformFee = channelPlatformFee;
    }

    public BigDecimal getChannelProfit() {
        return channelProfit;
    }

    public void setChannelProfit(BigDecimal channelProfit) {
        this.channelProfit = channelProfit;
    }

    public BigDecimal getSysChannelProfit() {
        return sysChannelProfit;
    }

    public void setSysChannelProfit(BigDecimal sysChannelProfit) {
        this.sysChannelProfit = sysChannelProfit;
    }

    public BigDecimal getAgentChannelProfit() {
        return agentChannelProfit;
    }

    public void setAgentChannelProfit(BigDecimal agentChannelProfit) {
        this.agentChannelProfit = agentChannelProfit;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getSysServiceProfit() {
        return sysServiceProfit;
    }

    public void setSysServiceProfit(BigDecimal sysServiceProfit) {
        this.sysServiceProfit = sysServiceProfit;
    }

    public BigDecimal getAgentServiceProfit() {
        return agentServiceProfit;
    }

    public void setAgentServiceProfit(BigDecimal agentServiceProfit) {
        this.agentServiceProfit = agentServiceProfit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPayContent() {
        return payContent;
    }

    public void setPayContent(String payContent) {
        this.payContent = payContent;
    }

    public String getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(String closeCause) {
        this.closeCause = closeCause;
    }

    public String getCloseType() {
        return closeType;
    }

    public void setCloseType(String closeType) {
        this.closeType = closeType;
    }

    public String getPlatformMapped() {
        return platformMapped;
    }

    public void setPlatformMapped(String platformMapped) {
        this.platformMapped = platformMapped;
    }
}