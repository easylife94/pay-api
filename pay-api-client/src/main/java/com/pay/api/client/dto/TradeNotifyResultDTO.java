package com.pay.api.client.dto;

import com.pay.api.client.constants.PlatformTradeNotifyResultEnum;
import com.pay.api.client.constants.TradeOrderStatusEnum;

import java.util.Date;

/**
 * @author chenwei
 * @date 2019-03-17
 */
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

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public PlatformTradeNotifyResultEnum getResult() {
        return result;
    }

    public void setResult(PlatformTradeNotifyResultEnum result) {
        this.result = result;
    }

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
    }

    public String getPlatformOrderNumber() {
        return platformOrderNumber;
    }

    public void setPlatformOrderNumber(String platformOrderNumber) {
        this.platformOrderNumber = platformOrderNumber;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPlatformNotifyTime() {
        return platformNotifyTime;
    }

    public void setPlatformNotifyTime(Date platformNotifyTime) {
        this.platformNotifyTime = platformNotifyTime;
    }

    public TradeOrderStatusEnum getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeOrderStatusEnum tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(String closeCause) {
        this.closeCause = closeCause;
    }

    public TradeNotifyResultDTO() {
    }

    public TradeNotifyResultDTO(String responseContent, PlatformTradeNotifyResultEnum result, String sysOrderNumber,
                                String platformOrderNumber, TradeOrderStatusEnum tradeStatus, Date payTime) {
        this.responseContent = responseContent;
        this.result = result;
        this.sysOrderNumber = sysOrderNumber;
        this.platformOrderNumber = platformOrderNumber;
        this.payTime = payTime;
        this.tradeStatus = tradeStatus;
    }
}
