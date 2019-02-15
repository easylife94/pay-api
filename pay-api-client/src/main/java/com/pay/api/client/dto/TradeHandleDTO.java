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

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeHandleDTO.class.getSimpleName() + "{", "}")
                .add("platformMapped=" + platformMapped)
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("tradeAmount=" + tradeAmount)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .toString();
    }
}
