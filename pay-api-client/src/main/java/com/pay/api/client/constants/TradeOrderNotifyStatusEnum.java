package com.pay.api.client.constants;

/**
 * 订单异步通知状态
 *
 * @author chenwei
 * @date 2019/1/30 14:52
 */
public enum TradeOrderNotifyStatusEnum {

    /**
     * 还没发起通知
     */
    WAIT("WAIT", "待通知"),

    /**
     * 通知成功
     */
    SUCCESS("SUCCESS", "通知成功"),

    /**
     * 发起通知但是失败
     */
    FAIL("FAIL", "通知失败"),

    /**
     * 发起通知全部失败
     */
    FINISHED("FINISHED", "通知结束"),
    ;

    private String type;
    private String name;

    TradeOrderNotifyStatusEnum(String type, String name) {
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
    }
}
