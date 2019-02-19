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
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 支付渠道
     */
    private String defrayalChannel;

    /**
     * 支付方式
     */
    private String defrayalType;

    @Override
    public String toString() {
        return new StringJoiner(", ", TradeRouteDTO.class.getSimpleName() + "[", "]")
                .add("memberNumber='" + memberNumber + "'")
                .add("platformNumber='" + platformNumber + "'")
                .add("channelNumber='" + channelNumber + "'")
                .add("merchantNumber='" + merchantNumber + "'")
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .toString();
    }

    public TradeRouteDTO(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                         String defrayalChannel, String defrayalType) {
        this.memberNumber = memberNumber;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
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

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
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

}
