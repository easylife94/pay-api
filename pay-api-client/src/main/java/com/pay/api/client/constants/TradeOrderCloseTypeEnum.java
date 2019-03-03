package com.pay.api.client.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 订单关闭类型枚举
 *
 * @author chenwei
 * @date 2019-03-03
 */
public enum TradeOrderCloseTypeEnum {

    /**
     * 向上游下单失败导致订单关闭
     */
    ORDER_ERROR("ORDER_ERROR", "下单失败"),

    /**
     * 查询订单后明确订单已关闭
     */
    TRADE_CLOSE("TRADE_CLOSE", "上游订单关闭"),

    ;

    private String type;
    private String name;

    TradeOrderCloseTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public static TradeOrderCloseTypeEnum getByType(String type) {
        for (TradeOrderCloseTypeEnum e : TradeOrderCloseTypeEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
