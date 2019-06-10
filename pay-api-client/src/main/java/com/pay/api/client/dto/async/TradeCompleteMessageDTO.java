package com.pay.api.client.dto.async;

import com.pay.common.client.constants.CheckDayEnum;
import com.pay.common.client.constants.CheckMethodEnum;
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

    /**
     * 结算日枚举
     */
    private CheckDayEnum checkDay;

    /**
     * 结算方式枚举
     */
    private CheckMethodEnum checkMethod;

    /**
     * 结算时间：小时
     */
    private Integer checkHour;

    /**
     * 结算时间：分钟
     */
    private Integer checkMin;
}
