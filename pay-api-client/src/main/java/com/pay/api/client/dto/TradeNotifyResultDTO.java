package com.pay.api.client.dto;

import com.pay.api.client.constants.PlatformTradeNotifyResultEnum;
import com.pay.api.client.constants.TradeOrderStatusEnum;
import lombok.Data;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019-03-17
 */
@Data
public class TradeNotifyResultDTO {

    /**
     * 响应内容
     */
    private String responseContent;

    /**
     * 回调结果
     */
    private PlatformTradeNotifyResultEnum result;

    /*订单详情*/

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 平台订单号
     */
    private String platformOrderNumber;

    /**
     * 源订单号（微信或支付宝等）
     */
    private String sourceOrderNumber;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 平台回调时间
     */
    private Date platformNotifyTime;

    /**
     * 交易状态
     */
    private TradeOrderStatusEnum tradeStatus;

    /**
     * 订单关闭原因
     */
    private String closeCause;
}
