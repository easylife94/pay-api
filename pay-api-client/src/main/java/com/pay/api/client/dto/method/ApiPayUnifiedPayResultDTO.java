package com.pay.api.client.dto.method;

import java.util.StringJoiner;

/**
 * 支付接口 - 统一支付返回结果DTO
 *
 * @author chenwei
 * @date 2019/1/17 10:12
 */
public class ApiPayUnifiedPayResultDTO {

    /**
     * 系统订单号
     * 必填
     */
    private String orderNumber;

    /**
     * 会员订单号
     * 必填
     */
    private String memberOrderNumber;

    /**
     * 系统下单时间
     * 格式：yyyy-MM-dd
     * 必填
     */
    private String orderTime;

    /**
     * 会员号
     * 必填
     */
    private String memberNumber;

    /**
     * 商户号
     * 必填
     */
    private String merchantNumber;

    /**
     * 币种，默认人民币，CNY
     * 必填
     */
    private String currency;

    /**
     * 订单状态
     * 必填
     */
    private String orderStatus;

    /**
     * 支付金额，用户实际支付金额
     * 必填
     */
    private String payAmount;

    /**
     * 服务费金额，系统从预充金额中扣除的服务费
     * 必填
     */
    private String serviceAmount;

    /**
     * 支付内容，可以是支付连接或者JSAPI json，反扫时为空
     * 非必填
     */
    private String content;

    public ApiPayUnifiedPayResultDTO(String orderNumber, String memberOrderNumber, String orderTime, String memberNumber, String merchantNumber,
                                     String currency, String orderStatus, String payAmount, String serviceAmount, String content) {
        this.orderNumber = orderNumber;
        this.memberOrderNumber = memberOrderNumber;
        this.orderTime = orderTime;
        this.memberNumber = memberNumber;
        this.merchantNumber = merchantNumber;
        this.currency = currency;
        this.orderStatus = orderStatus;
        this.payAmount = payAmount;
        this.serviceAmount = serviceAmount;
        this.content = content;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiPayUnifiedPayResultDTO.class.getSimpleName() + "[", "]")
                .add("orderNumber='" + orderNumber + "'")
                .add("memberOrderNumber='" + memberOrderNumber + "'")
                .add("orderTime='" + orderTime + "'")
                .add("memberNumber='" + memberNumber + "'")
                .add("merchantNumber='" + merchantNumber + "'")
                .add("currency='" + currency + "'")
                .add("orderStatus='" + orderStatus + "'")
                .add("payAmount='" + payAmount + "'")
                .add("serviceAmount='" + serviceAmount + "'")
                .add("content='" + content + "'")
                .toString();
    }
}
