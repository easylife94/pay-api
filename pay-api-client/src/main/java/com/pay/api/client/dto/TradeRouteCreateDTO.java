package com.pay.api.client.dto;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 路由新增dto
 *
 * @author chenwei
 * @date 2019-02-18
 */
public class TradeRouteCreateDTO {

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 平台编号
     */
    private String platformNumber;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 支付渠道
     */
    private String defrayalChannel;

    /**
     * 支付方式
     */
    private String defrayalType;

    /**
     * 禁用状态
     */
    private Boolean status;

    /**
     * 单笔最小交易金额
     */
    private BigDecimal singleTradeAmountMin;

    /**
     * 单笔最大交易金额
     */
    private BigDecimal singleTradeAmountMax;

    /**
     * 交易时间间隔。单位毫秒
     */
    private Long tradeIntervalTime;

    public TradeRouteCreateDTO(String memberNumber, String platformNumber, String channelNumber, Long merchantId,
                               String merchantNumber, String merchantName, String defrayalChannel, String defrayalType,
                               Boolean status, BigDecimal singleTradeAmountMin, BigDecimal singleTradeAmountMax, Long tradeIntervalTime) {
        this.memberNumber = memberNumber;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantId = merchantId;
        this.merchantNumber = merchantNumber;
        this.merchantName = merchantName;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.status = status;
        this.singleTradeAmountMin = singleTradeAmountMin;
        this.singleTradeAmountMax = singleTradeAmountMax;
        this.tradeIntervalTime = tradeIntervalTime;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
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

    public String getDefrayalChannel() {
        return defrayalChannel;
    }

    public void setDefrayalChannel(String defrayalChannel) {
        this.defrayalChannel = defrayalChannel;
    }

    public String getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(String defrayalType) {
        this.defrayalType = defrayalType;
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

    public Long getTradeIntervalTime() {
        return tradeIntervalTime;
    }

    public void setTradeIntervalTime(Long tradeIntervalTime) {
        this.tradeIntervalTime = tradeIntervalTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeRouteCreateDTO.class.getSimpleName() + "{", "}")
                .add("memberNumber=" + memberNumber)
                .add("platformNumber=" + platformNumber)
                .add("channelNumber=" + channelNumber)
                .add("merchantId=" + merchantId)
                .add("merchantNumber=" + merchantNumber)
                .add("merchantName=" + merchantName)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .add("status=" + status)
                .add("singleTradeAmountMin=" + singleTradeAmountMin)
                .add("singleTradeAmountMax=" + singleTradeAmountMax)
                .add("tradeIntervalTime=" + tradeIntervalTime)
                .toString();
    }
}
