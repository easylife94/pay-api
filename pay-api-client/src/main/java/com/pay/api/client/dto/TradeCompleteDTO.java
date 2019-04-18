package com.pay.api.client.dto;

import lombok.Data;

import java.util.Date;

/**
 * 交易完成参数DTO
 *
 * @author chenwei
 * @date 2019-03-20
 */
@Data
public class TradeCompleteDTO {

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 通道订单号
     */
    private String channelOrderNumber;

    /**
     * 渠道订单号（微信/支付宝等）
     */
    private String sourceOrderNumber;

    /**
     * 支付成功
     */
    private Date payTime;

    /**
     * 回调时间
     */
    private Date notifyTime;

    public TradeCompleteDTO(String sysOrderNumber, String channelOrderNumber, String sourceOrderNumber, Date payTime, Date notifyTime) {
        this.sysOrderNumber = sysOrderNumber;
        this.channelOrderNumber = channelOrderNumber;
        this.sourceOrderNumber = sourceOrderNumber;
        this.payTime = payTime;
        this.notifyTime = notifyTime;
    }
}
