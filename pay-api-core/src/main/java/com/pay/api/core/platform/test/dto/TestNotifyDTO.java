package com.pay.api.core.platform.test.dto;

/**
 * @author chenwei
 * @date 2019-03-17
 */
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSourceTradeNo() {
        return sourceTradeNo;
    }

    public void setSourceTradeNo(String sourceTradeNo) {
        this.sourceTradeNo = sourceTradeNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

}
