package com.pay.api.client.dto;

import lombok.Data;

import java.util.Date;

/**
 * 交易路由商户DTO
 *
 * @author chenwei
 * @date 2019/1/17 15:21
 */
@Data
public class TradeRouteMerchantDTO {

    private Long tradeRouteId;

    private String platformMapped;

    private Long platformId;

    private String platformNumber;

    private String platformName;

    private Long channelId;

    private String channelNumber;

    private String channelName;

    private Long merchantId;

    private String merchantNumber;

    private String merchantName;

    private Date tradeWarnDate;

    private Integer tradeWarnTimes;

    public TradeRouteMerchantDTO(Long tradeRouteId, String platformMapped, Long platformId, String platformNumber, String platformName, Long channelId, String channelNumber,
                                 String channelName, Long merchantId, String merchantNumber, String merchantName, Date tradeWarnDate, Integer tradeWarnTimes) {
        this.tradeRouteId = tradeRouteId;
        this.platformMapped = platformMapped;
        this.platformId = platformId;
        this.platformNumber = platformNumber;
        this.platformName = platformName;
        this.channelId = channelId;
        this.channelNumber = channelNumber;
        this.channelName = channelName;
        this.merchantId = merchantId;
        this.merchantNumber = merchantNumber;
        this.merchantName = merchantName;
        this.tradeWarnDate = tradeWarnDate;
        this.tradeWarnTimes = tradeWarnTimes;
    }
}
