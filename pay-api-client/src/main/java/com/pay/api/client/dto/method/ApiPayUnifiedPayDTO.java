package com.pay.api.client.dto.method;

import javax.validation.constraints.NotBlank;
import java.util.StringJoiner;

/**
 * 支付接口 - 统一支付参数DTO
 *
 * @author chenwei
 * @date 2019/1/17 11:27
 */
public class ApiPayUnifiedPayDTO {

    /**
     * 会员编号
     * 必填
     */
    private String memberNumber;

    /**
     * 平台编号，不为空则轮循平台下商户
     * 非必填
     */
    private String platformNumber;

    /**
     * 通道编号，不为空则轮循通道下商户
     */
    private String channelNumber;

    /**
     * 商户号，直接发起对商户交易
     * 非必填
     */
    private String merchantNumber;

    /**
     * 交易金额，单位：元。最小值0.01
     * 必填
     */
    private String tradeAmount;

    /**
     * 币种，默认值：CNY
     * 必填
     */
    private String currency;

    /**
     * 会员订单号，会员下唯一
     * 必填
     */
    private String memberOrderNumber;

    /**
     * 反扫授权码，支付方式为反扫时必填
     * 非必填
     */
    private String authCode;

    /**
     * 支付渠道，微信，支付宝，qq
     * 必填
     */
    private String defrayalChannel;

    /**
     * 支付方式，正扫，反扫，H5，app
     * 必填
     */
    private String defrayalType;

    /**
     * 同步地址
     * 非必填
     */
    private String returnUrl;

    /**
     * 通知地址
     * 必填
     */
    private String notifyUrl;

    /**
     * 订单标题
     * 必填
     */
    private String subject;

    /**
     * 订单描述
     * 非必填
     */
    private String body;

    /**
     * 附加信息，通知和查询时原值返回
     */
    private String attach;

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getDefrayalChannel() {
        return defrayalChannel;
    }

    public void setDefrayalChannel(String defrayalChannel) {
        this.defrayalChannel = defrayalChannel;
    }

    public String getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(String defrayalType) {
        this.defrayalType = defrayalType;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiPayUnifiedPayDTO.class.getSimpleName() + "[", "]")
                .add("memberNumber='" + memberNumber + "'")
                .add("platformNumber='" + platformNumber + "'")
                .add("channelNumber='" + channelNumber + "'")
                .add("merchantNumber='" + merchantNumber + "'")
                .add("tradeAmount='" + tradeAmount + "'")
                .add("currency='" + currency + "'")
                .add("memberOrderNumber='" + memberOrderNumber + "'")
                .add("authCode='" + authCode + "'")
                .add("defrayalChannel='" + defrayalChannel + "'")
                .add("defrayalType='" + defrayalType + "'")
                .add("returnUrl='" + returnUrl + "'")
                .add("notifyUrl='" + notifyUrl + "'")
                .add("subject='" + subject + "'")
                .add("body='" + body + "'")
                .add("attach='" + attach + "'")
                .toString();
    }
}
