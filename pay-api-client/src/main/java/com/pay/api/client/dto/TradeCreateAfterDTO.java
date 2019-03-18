package com.pay.api.client.dto;

import lombok.Data;

import java.util.StringJoiner;

/**
 * 交易创建后参数DTO
 *
 * @author chenwei
 * @date 2019/2/21 11:44
 */
@Data
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
}
