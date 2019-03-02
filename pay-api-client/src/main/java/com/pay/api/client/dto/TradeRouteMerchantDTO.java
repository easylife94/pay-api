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

    private String platformNumber;

    private String channelNumber;

    private String merchantNumber;

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

    public TradeRouteMerchantDTO(Long tradeRouteId, String platformMapped, String platformNumber, String channelNumber, String merchantNumber) {
        this.tradeRouteId = tradeRouteId;
        this.platformMapped = platformMapped;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeRouteMerchantDTO.class.getSimpleName() + "{", "}")
                .add("tradeRouteId=" + tradeRouteId)
                .add("platformMapped=" + platformMapped)
                .add("platformNumber=" + platformNumber)
                .add("channelNumber=" + channelNumber)
                .add("merchantNumber=" + merchantNumber)
                .toString();
    }
}
