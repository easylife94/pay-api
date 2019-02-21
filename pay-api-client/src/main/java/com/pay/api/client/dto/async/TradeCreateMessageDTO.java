package com.pay.api.client.dto.async;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 交易创建消息DTO
 *
 * @author chenwei
 * @date 2019-02-20
 */
public class TradeCreateMessageDTO implements Serializable {

    private static final long serialVersionUID = 3384362006328832159L;

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

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
    }

    public Long getTradeRouteId() {
        return tradeRouteId;
    }

    public void setTradeRouteId(Long tradeRouteId) {
        this.tradeRouteId = tradeRouteId;
    }

    public Boolean getTradeRisk() {
        return tradeRisk;
    }

    public void setTradeRisk(Boolean tradeRisk) {
        this.tradeRisk = tradeRisk;
    }

    public Boolean getTradeWarn() {
        return tradeWarn;
    }

    public void setTradeWarn(Boolean tradeWarn) {
        this.tradeWarn = tradeWarn;
    }

    public Long getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(Long tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }

    public TradeCreateMessageDTO(String sysOrderNumber, Long tradeRouteId, Boolean tradeRisk, Boolean tradeWarn, Long tradeTimestamp) {
        this.sysOrderNumber = sysOrderNumber;
        this.tradeRouteId = tradeRouteId;
        this.tradeRisk = tradeRisk;
        this.tradeWarn = tradeWarn;
        this.tradeTimestamp = tradeTimestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeCreateMessageDTO.class.getSimpleName() + "{", "}")
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("tradeRouteId=" + tradeRouteId)
                .add("tradeRisk=" + tradeRisk)
                .add("tradeWarn=" + tradeWarn)
                .add("tradeTimestamp=" + tradeTimestamp)
                .toString();
    }
}
