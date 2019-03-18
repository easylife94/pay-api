package com.pay.api.client.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 路由新增dto
 *
 * @author chenwei
 * @date 2019-02-18
 */
@Data
public class TradeRouteCreateDTO {

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
     * 商户id
     */
    private Long merchantId;

    /**
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 支付渠道
     */
    private String defrayalChannel;

    /**
     * 支付方式
     */
    private String defrayalType;

    /**
     * 禁用状态
     */
    private Boolean status;

    /**
     * 单笔最小交易金额
     */
    private BigDecimal singleTradeAmountMin;

    /**
     * 单笔最大交易金额
     */
    private BigDecimal singleTradeAmountMax;

    /**
     * 交易时间间隔。单位毫秒
     */
    private Long tradeIntervalTime;

    public TradeRouteCreateDTO(String memberNumber, String platformNumber, String channelNumber, Long merchantId,
                               String merchantNumber, String merchantName, String defrayalChannel, String defrayalType,
                               Boolean status, BigDecimal singleTradeAmountMin, BigDecimal singleTradeAmountMax, Long tradeIntervalTime) {
        this.memberNumber = memberNumber;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantId = merchantId;
        this.merchantNumber = merchantNumber;
        this.merchantName = merchantName;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.status = status;
        this.singleTradeAmountMin = singleTradeAmountMin;
        this.singleTradeAmountMax = singleTradeAmountMax;
        this.tradeIntervalTime = tradeIntervalTime;
    }
}
