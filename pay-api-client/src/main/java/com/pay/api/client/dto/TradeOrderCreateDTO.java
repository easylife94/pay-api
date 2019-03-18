package com.pay.api.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/1/30 16:17
 */
@Data
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
}
