package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易创建后参数DTO
 *
 * @author chenwei
 * @date 2019/2/21 11:44
 */
public class TradeCreateAfterDTO {

    /**
     * 交易路由id
     */
    private Long tradeRouteId;

    /**
     * 是否交易风控
     */
    private Boolean tradeRisk;

    /**
     * 是否交易预警
     */
    private Boolean tradeWarn;

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

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeCreateAfterDTO.class.getSimpleName() + "{", "}")
                .add("tradeRouteId=" + tradeRouteId)
                .add("tradeRisk=" + tradeRisk)
                .add("tradeWarn=" + tradeWarn)
                .toString();
    }
}
