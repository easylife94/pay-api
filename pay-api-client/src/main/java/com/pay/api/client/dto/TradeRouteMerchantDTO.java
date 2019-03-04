package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易路由商户DTO
 *
 * @author chenwei
 * @date 2019/1/17 15:21
 */
public class TradeRouteMerchantDTO {

    private Long tradeRouteId;

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

    public Long getTradeRouteId() {
        return tradeRouteId;
    }

    public void setTradeRouteId(Long tradeRouteId) {
        this.tradeRouteId = tradeRouteId;
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
        this.platformNumber = platformNumber;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


    public TradeRouteMerchantDTO(Long tradeRouteId, String platformMapped, Long platformId, String platformNumber, String platformName, Long channelId, String channelNumber,
                                 String channelName, Long merchantId, String merchantNumber, String merchantName) {
        this.tradeRouteId = tradeRouteId;
        this.platformMapped = platformMapped;
        this.platformId = platformId;
        this.platformNumber = platformNumber;
        this.platformName = platformName;
        this.channelId = channelId;
        this.channelNumber = channelNumber;
        this.channelName = channelName;
        this.merchantId = merchantId;
        this.merchantNumber = merchantNumber;
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeRouteMerchantDTO.class.getSimpleName() + "{", "}")
                .add("tradeRouteId=" + tradeRouteId)
                .add("platformMapped=" + platformMapped)
                .add("platformId=" + platformId)
                .add("platformNumber=" + platformNumber)
                .add("platformName=" + platformName)
                .add("channelId=" + channelId)
                .add("channelNumber=" + channelNumber)
                .add("channelName=" + channelName)
                .add("merchantId=" + merchantId)
                .add("merchantNumber=" + merchantNumber)
                .add("merchantName=" + merchantName)
                .toString();
    }
}
