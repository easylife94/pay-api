package com.pay.api.client.dto.async;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chenwei
 * @date 2019-04-17
 */
@Data
public class TradeCompleteMessageDTO {

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    /**
     * 拥有者编号
     */
    private String ownNumber;

    /**
     * 交易时间
     */
    private Date tradeTime;


}
