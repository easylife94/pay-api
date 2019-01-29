package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易路由参数DTO
 *
 * @author chenwei
 * @date 2019/1/17 15:25
 */
public class TradeRouteDTO {

    /**
     * 平台编号
     */
    private String platformNumber;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 商户编号
     */
    private String merchantNumber;




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

    @Override
    public String toString() {
        return new StringJoiner(", ", TradeRouteDTO.class.getSimpleName() + "[", "]")
                .add("platformNumber='" + platformNumber + "'")
                .add("channelNumber='" + channelNumber + "'")
                .add("merchantNumber='" + merchantNumber + "'")
                .toString();
    }
}
