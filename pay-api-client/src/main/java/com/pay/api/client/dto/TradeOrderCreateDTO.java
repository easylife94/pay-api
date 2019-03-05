package com.pay.api.client.dto;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/1/30 16:17
 */
public class TradeOrderCreateDTO {

    private Long memberId;
    private String memberNumber;
    private String memberName;
    private Long agentId;
    private String agentNumber;
    private String agentName;
    private String agentLevel;
    private String defrayalChannel;
    private String defrayalType;
    private String memberOrderNumber;
    private BigDecimal tradeAmount;
    private Long merchantId;
    private String merchantNumber;
    private String merchantName;
    private Long platformId;
    private String platformMapped;
    private String platformNumber;
    private String platformName;
    private Long channelId;
    private String channelNumber;
    private String channelName;
    private String title;
    private String body;
    private String attach;


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
        this.memberNumber = memberNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
        this.agentNumber = agentNumber;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
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

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber;
    }

    public String getPlatformMapped() {
        return platformMapped;
    }

    public void setPlatformMapped(String platformMapped) {
        this.platformMapped = platformMapped;
    }

    public TradeOrderCreateDTO(Long memberId, String memberNumber, String memberName, Long agentId, String agentNumber, String agentName,
                               String agentLevel, String defrayalChannel, String defrayalType, String memberOrderNumber, BigDecimal tradeAmount,
                               Long merchantId, String merchantNumber, String merchantName, Long platformId,String platformMapped, String platformNumber, String platformName,
                               Long channelId, String channelNumber, String channelName, String title, String body, String attach) {
        this.memberId = memberId;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.agentId = agentId;
        this.agentNumber = agentNumber;
        this.agentName = agentName;
        this.agentLevel = agentLevel;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.memberOrderNumber = memberOrderNumber;
        this.tradeAmount = tradeAmount;
        this.merchantId = merchantId;
        this.merchantNumber = merchantNumber;
        this.merchantName = merchantName;
        this.platformId = platformId;
        this.platformMapped = platformMapped;
        this.platformNumber = platformNumber;
        this.platformName = platformName;
        this.channelId = channelId;
        this.channelNumber = channelNumber;
        this.channelName = channelName;
        this.title = title;
        this.body = body;
        this.attach = attach;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
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
        this.platformNumber = platformNumber;
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

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeOrderCreateDTO.class.getSimpleName() + "{", "}")
                .add("memberId=" + memberId)
                .add("memberNumber=" + memberNumber)
                .add("memberName=" + memberName)
                .add("agentId=" + agentId)
                .add("agentNumber=" + agentNumber)
                .add("agentName=" + agentName)
                .add("agentLevel=" + agentLevel)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .add("memberOrderNumber=" + memberOrderNumber)
                .add("tradeAmount=" + tradeAmount)
                .add("merchantId=" + merchantId)
                .add("merchantNumber=" + merchantNumber)
                .add("merchantName=" + merchantName)
                .add("platformId=" + platformId)
                .add("platformNumber=" + platformNumber)
                .add("platformName=" + platformName)
                .add("channelId=" + channelId)
                .add("channelNumber=" + channelNumber)
                .add("channelName=" + channelName)
                .add("title=" + title)
                .add("body=" + body)
                .add("attach=" + attach)
                .toString();
    }
}
