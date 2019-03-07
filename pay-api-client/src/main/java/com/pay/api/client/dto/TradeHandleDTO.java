package com.pay.api.client.dto;

import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 交易处理参数dto
 *
 * @author chenwei
 * @date 2019-01-29
 */
public class TradeHandleDTO {

    /**
     * 平台标识
     */
    private String platformMapped;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 支付渠道
     */
    private DefrayalChannelEnum defrayalChannel;

    /**
     * 支付方式
     */
    private DefrayalTypeEnum defrayalType;

    /**
     * 微信openId或支付宝buyer id
     */
    private String userId;


    public TradeHandleDTO(String platformMapped, String channelNumber, String merchantNumber, String sysOrderNumber, BigDecimal tradeAmount,
                          DefrayalChannelEnum defrayalChannel, DefrayalTypeEnum defrayalType, String userId) {
        this.platformMapped = platformMapped;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
        this.sysOrderNumber = sysOrderNumber;
        this.tradeAmount = tradeAmount;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.userId = userId;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getPlatformMapped() {
        return platformMapped;
    }

    public void setPlatformMapped(String platformMapped) {
        this.platformMapped = platformMapped;
    }

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public DefrayalChannelEnum getDefrayalChannel() {
        return defrayalChannel;
    }

    public void setDefrayalChannel(DefrayalChannelEnum defrayalChannel) {
        this.defrayalChannel = defrayalChannel;
    }

    public DefrayalTypeEnum getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(DefrayalTypeEnum defrayalType) {
        this.defrayalType = defrayalType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeHandleDTO.class.getSimpleName() + "{", "}")
                .add("platformMapped=" + platformMapped)
                .add("channelNumber=" + channelNumber)
                .add("merchantNumber=" + merchantNumber)
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("tradeAmount=" + tradeAmount)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .add("userId=" + userId)
                .toString();
    }
}
