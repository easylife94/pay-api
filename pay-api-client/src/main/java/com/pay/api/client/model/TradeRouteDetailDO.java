package com.pay.api.client.model;

import com.pay.common.client.model.AbstractBaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenwei
 * @date 2019-04-22
 */
@Data
public class TradeRouteDetailDO extends AbstractBaseDO {

    private Long tradeRouteId;

    private BigDecimal tradeAmountMin;

    private BigDecimal tradeAmountMax;

    private String platformChannelFeeType;

    private BigDecimal platformChannelFee;

    private String sysChannelProfitType;

    private BigDecimal sysChannelProfit;

}
