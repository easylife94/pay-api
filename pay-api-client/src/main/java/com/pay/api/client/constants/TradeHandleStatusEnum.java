package com.pay.api.client.constants;

/**
 * 交易处理状态
 *
 * @author chenwei
 * @date 2019/1/30 10:54
 */
public enum TradeHandleStatusEnum {

    /**
     * 处理成功
     */
    SUCCESS("SUCCESS", "处理成功"),

    /**
     * 未知
     */
    UNKNOWN("UNKNOWN", "未知"),

    /**
     * 处理失败
     */
    ERROR("ERROR", "处理失败"),

    /**
     * 命中风控
     */
    RISK("RISK","命中风控"),
    ;

    private String type;
    private String name;

    TradeHandleStatusEnum(String type, String name) {
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
