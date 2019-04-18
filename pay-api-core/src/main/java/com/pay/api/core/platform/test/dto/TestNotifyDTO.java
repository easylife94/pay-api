package com.pay.api.core.platform.test.dto;

import lombok.Data;

/**
 * @author chenwei
 * @date 2019-03-17
 */
@Data
public class TestNotifyDTO {

    /**
     * 外部订单号
     */
    private String outTradeNo;

    /**
     * TEST平台订单号
     */
    private String tradeNo;

    /**
     * 源订单号
     */
    private String sourceTradeNo;

    /**
     * 支付时间
     */
    private String payTime;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 签名
     */
    private String sign;

}
