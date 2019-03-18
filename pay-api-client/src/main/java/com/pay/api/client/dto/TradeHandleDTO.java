package com.pay.api.client.dto;

import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 交易处理参数dto
 *
 * @author chenwei
 * @date 2019-01-29
 */
@Data
public class TradeHandleDTO {

    /**
     * 平台标识
     */
    private String platformMapped;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 支付渠道
     */
    private DefrayalChannelEnum defrayalChannel;

    /**
     * 支付方式
     */
    private DefrayalTypeEnum defrayalType;

    /**
     * 微信openId或支付宝buyer id
     */
    private String userId;


    public TradeHandleDTO(String platformMapped, String channelNumber, String merchantNumber, String sysOrderNumber, BigDecimal tradeAmount,
                          DefrayalChannelEnum defrayalChannel, DefrayalTypeEnum defrayalType, String userId) {
        this.platformMapped = platformMapped;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
        this.sysOrderNumber = sysOrderNumber;
        this.tradeAmount = tradeAmount;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.userId = userId;
    }
}
