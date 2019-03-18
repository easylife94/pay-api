package com.pay.api.client.dto.mapper;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员交易路由
 *
 * @author chenwei
 * @date 2019/3/14 17:41
 */
@Data
public class MemberTradeRouteDTO {
    private Long id;
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
    private Long lastTradeTimestamp;
    private BigDecimal singleTradeAmountMin;
    private BigDecimal singleTradeAmountMax;
    private Long tradeIntervalTime;
}
