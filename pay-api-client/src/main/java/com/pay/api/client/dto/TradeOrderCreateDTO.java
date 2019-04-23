package com.pay.api.client.dto;

import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.utils.FeeUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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
    private Long tradeRouteId;
    private List<FeeUtils.Fee> serviceFees;

    public TradeOrderCreateDTO(ApiPayUnifiedPayDTO apiPayUnifiedPayDTO, TradeMemberDTO memberDTO, TradeRouteMerchantDTO tradeRouteDTO) {

        this.memberOrderNumber = apiPayUnifiedPayDTO.getMemberOrderNumber();
        this.tradeAmount = new BigDecimal(apiPayUnifiedPayDTO.getTradeAmount());
        this.defrayalChannel = apiPayUnifiedPayDTO.getDefrayalChannel();
        this.defrayalType = apiPayUnifiedPayDTO.getDefrayalType();
        this.title = apiPayUnifiedPayDTO.getTitle();
        this.body = apiPayUnifiedPayDTO.getBody();
        this.attach = apiPayUnifiedPayDTO.getAttach();

        this.memberId = memberDTO.getMemberId();
        this.memberNumber = memberDTO.getMemberNumber();
        this.memberName = memberDTO.getMemberName();
        this.agentId = memberDTO.getAgentId();
        this.agentNumber = memberDTO.getAgentNumber();
        this.agentName = memberDTO.getAgentName();
        this.agentLevel = memberDTO.getAgentLevel();

        this.merchantId = tradeRouteDTO.getMerchantId();
        this.merchantNumber = tradeRouteDTO.getMerchantNumber();
        this.merchantName = tradeRouteDTO.getMerchantName();
        this.platformId = tradeRouteDTO.getPlatformId();
        this.platformMapped = tradeRouteDTO.getPlatformMapped();
        this.platformNumber = tradeRouteDTO.getPlatformNumber();
        this.platformName = tradeRouteDTO.getPlatformName();
        this.channelId = tradeRouteDTO.getChannelId();
        this.channelNumber = tradeRouteDTO.getChannelNumber();
        this.channelName = tradeRouteDTO.getChannelName();
        this.tradeRouteId = tradeRouteDTO.getTradeRouteId();

        this.serviceFees = memberDTO.getServiceFees();

    }
}
