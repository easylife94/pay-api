package com.pay.api.client.dto.method;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 订单查询结果dto
 *
 * @author chenwei
 * @date 2019/2/20 10:14
 */
public class ApiPayOrderQueryResultDTO implements Serializable {

    private static final long serialVersionUID = 126603657709497916L;

    /**
     * 会员订单号
     * 必填
     */
    private String memberOrderNumber;

    /**
     * 系统订单号
     * 必填
     */
    private String sysOrderNumber;

    /**
     * 交易金额
     * 必填
     */
    private String tradeAmount;

    /**
     * 币种
     * 必填
     */
    private String currency;

    /**
     * 下单时间
     * 必填
     */
    private String createTime;

    /**
     * 支付时间
     * 非必填
     */
    private String payTime;

    /**
     * 交易状态
     * 必填
     */
    private String tradeStatus;

    /**
     * 支付渠道
     * 必填
     */
    private String defrayalChannel;

    /**
     * 支付类型
     * 必填
     */
    private String defrayalType;

    /**
     * 附加信息
     * 非必填
     */
    private String attach;

    public ApiPayOrderQueryResultDTO(String memberOrderNumber, String sysOrderNumber, String tradeAmount, String currency,
                                     String createTime, String tradeStatus, String defrayalChannel, String defrayalType) {
        this.memberOrderNumber = memberOrderNumber;
        this.sysOrderNumber = sysOrderNumber;
        this.tradeAmount = tradeAmount;
        this.currency = currency;
        this.createTime = createTime;
        this.tradeStatus = tradeStatus;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
    }

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber;
    }

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                ApiPayOrderQueryResultDTO.class.getSimpleName() + "{", "}")
                .add("memberOrderNumber=" + memberOrderNumber)
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("tradeAmount=" + tradeAmount)
                .add("currency=" + currency)
                .add("createTime=" + createTime)
                .add("payTime=" + payTime)
                .add("tradeStatus=" + tradeStatus)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .add("attach=" + attach)
                .toString();
    }
}
