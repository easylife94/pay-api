package com.pay.api.client.dto;

import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;

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
    private DefrayalChannelEnum defrayalChannelEnum;

    /**
     * 支付方式
     */
    private DefrayalTypeEnum defrayalTypeEnum;

    @Override
    public String toString() {
        return new StringJoiner(", ", TradeRouteDTO.class.getSimpleName() + "[", "]")
                .add("memberNumber='" + memberNumber + "'")
                .add("platformNumber='" + platformNumber + "'")
                .add("channelNumber='" + channelNumber + "'")
                .add("merchantNumber='" + merchantNumber + "'")
                .add("defrayalChannelEnum=" + defrayalChannelEnum)
                .add("defrayalTypeEnum=" + defrayalTypeEnum)
                .toString();
    }

    public TradeRouteDTO(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                         DefrayalChannelEnum defrayalChannelEnum, DefrayalTypeEnum defrayalTypeEnum) {
        this.memberNumber = memberNumber;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
        this.defrayalChannelEnum = defrayalChannelEnum;
        this.defrayalTypeEnum = defrayalTypeEnum;
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

    public DefrayalChannelEnum getDefrayalChannelEnum() {
        return defrayalChannelEnum;
    }

    public void setDefrayalChannelEnum(DefrayalChannelEnum defrayalChannelEnum) {
        this.defrayalChannelEnum = defrayalChannelEnum;
    }

    public DefrayalTypeEnum getDefrayalTypeEnum() {
        return defrayalTypeEnum;
    }

    public void setDefrayalTypeEnum(DefrayalTypeEnum defrayalTypeEnum) {
        this.defrayalTypeEnum = defrayalTypeEnum;
    }

}
