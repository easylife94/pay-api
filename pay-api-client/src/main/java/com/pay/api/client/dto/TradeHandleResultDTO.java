package com.pay.api.client.dto;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import lombok.Data;

/**
 * 交易处理返回结果
 *
 * @author chenwei
 * @date 2019-01-29
 */
@Data
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
}
