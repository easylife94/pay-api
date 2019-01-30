package com.pay.api.client.dto;

import com.pay.api.client.constants.TradeHandleStatusEnum;

import java.util.StringJoiner;

/**
 * 交易处理返回结果
 *
 * @author chenwei
 * @date 2019-01-29
 */
public class TradeHandleResultDTO {

    /**
     * 处理状态
     */
    private TradeHandleStatusEnum status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 支付内容
     */
    private String content;

    /**
     * 平台订单号
     */
    private String platformOrderNumber;

    public TradeHandleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TradeHandleStatusEnum status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlatformOrderNumber() {
        return platformOrderNumber;
    }

    public void setPlatformOrderNumber(String platformOrderNumber) {
        this.platformOrderNumber = platformOrderNumber;
    }


    /**
     * 处理状态缺省值为UNKNOWN
     *
     * @param status              处理状态
     * @param errorMsg            错误信息
     * @param content             支付内容
     * @param platformOrderNumber 平台订单号
     */
    public TradeHandleResultDTO(TradeHandleStatusEnum status, String errorMsg, String content, String platformOrderNumber) {
        this.status = (status == null ? TradeHandleStatusEnum.UNKNOWN : status);
        this.errorMsg = errorMsg;
        this.content = content;
        this.platformOrderNumber = platformOrderNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeHandleResultDTO.class.getSimpleName() + "{", "}")
                .add("status=" + status)
                .add("errorMsg=" + errorMsg)
                .add("content=" + content)
                .add("platformOrderNumber=" + platformOrderNumber)
                .toString();
    }
}
