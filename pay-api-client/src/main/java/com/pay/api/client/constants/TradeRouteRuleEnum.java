package com.pay.api.client.constants;

/**
 * 交易路由规则
 *
 * @author chenwei
 * @date 2019/2/19 14:47
 */
public enum TradeRouteRuleEnum {

    /**
     * 交易轮循
     */
    ROUND_ROBIN("ROUND_ROBIN", "轮循"),



    ;

    private String type;
    private String name;

    TradeRouteRuleEnum(String type, String name) {
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
