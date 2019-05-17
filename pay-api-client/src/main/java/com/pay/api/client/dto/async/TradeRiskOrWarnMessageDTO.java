package com.pay.api.client.dto.async;

import lombok.Data;

/**
 * 交易风控或预警消息DTO
 *
 * @author chenwei
 * @date 2019/3/5 18:03
 */
@Data
public class TradeRiskOrWarnMessageDTO {

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 交易路由Id
     */
    private Long tradeRouteId;

    /**
     * 交易风控
     */
    private Boolean tradeRisk;

    /**
     * 交易预警
     */
    private Boolean tradeWarn;

    /**
     * 交易时间戳
     */
    private Long tradeTimestamp;

    public TradeRiskOrWarnMessageDTO(String sysOrderNumber, Long tradeRouteId, Boolean tradeRisk, Boolean tradeWarn, Long tradeTimestamp) {
        this.sysOrderNumber = sysOrderNumber;
        this.tradeRouteId = tradeRouteId;
        this.tradeRisk = tradeRisk;
        this.tradeWarn = tradeWarn;
        this.tradeTimestamp = tradeTimestamp;
    }
}
