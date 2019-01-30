package com.pay.api.client.constants;

/**
 * 订单结算状态枚举类
 *
 * @author chenwei
 * @date 2019/1/30 14:41
 */
public enum TradeOrderCheckStatusEnum {
    /**
     * 待结算
     */
    WAIT("WAIT", "待结算"),

    /**
     * 结算成功
     */
    SUCCESS("SUCCESS", "结算成功"),
    ;


    private String type;
    private String name;

    TradeOrderCheckStatusEnum(String type, String name) {
        this.type = type;
        this.name = name;
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
    }}
