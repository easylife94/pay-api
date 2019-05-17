package com.pay.api.client.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author chenwei
 */
@Data
public class TradeRouteDO implements Serializable {

    private static final long serialVersionUID = 4573642849152073055L;

    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private String memberNumber;

    private String platformMapped;

    private Long platformId;

    private String platformNumber;

    private String platformName;

    private Long channelId;

    private String channelNumber;

    private String channelName;

    private Long merchantId;

    private String merchantNumber;

    private String defrayalChannel;

    private String defrayalType;

    private String merchantName;

    private Boolean status;

    private BigDecimal singleTradeAmountMin;

    private BigDecimal singleTradeAmountMax;

    private Boolean tradeLimit;

    private Date tradeLimitTime;

    private Boolean tradeRisk;

    private Date tradeRiskTime;

    private Date tradeWarnDate;

    private Integer tradeWarnTimes;

    private Long tradeIntervalTime;

    private Long lastTradeTimestamp;

    private Long lastTradeSucTimestamp;

    private BigDecimal dayTradeSucAmountTotal;

    private BigDecimal monthTradeSucAmountTotal;
}