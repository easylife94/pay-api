package com.pay.api.client.constants;

/**
 * 交易订单币种枚举
 *
 * @author chenwei
 * @date 2019/1/30 14:57
 */
public enum TradeOrderCurrencyEnum {

    /**
     * 人民币
     */
    CNY("CNY", "人民币"),
    ;

    private String type;
    private String name;

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

    TradeOrderCurrencyEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
