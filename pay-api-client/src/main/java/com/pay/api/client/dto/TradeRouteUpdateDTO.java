package com.pay.api.client.dto;

import lombok.Data;

import java.util.Date;

/**
 * 交易路由更新DTO
 *
 * @author chenwei
 * @date 2019/3/18 17:20
 */
@Data
public class TradeRouteUpdateDTO {

    private Long id;

    /**
     * 是否交易风控
     */
    private Boolean tradeRisk;

    /**
     * 是否交易预警
     */
    private Boolean tradeWarn;

    /**
     * 路由交易预警次数
     */
    private Integer tradeWarnTimes;

    /**
     * 路由交易预警日期
     */
    private Date tradeWarnDate;
}
