package com.pay.api.client.constants;

/**
 * 交易订单状态枚举
 *
 * @author chenwei
 * @date 2019/1/30 10:36
 */
public enum TradeOrderStatusEnum {
    /**
     * 创建订单,等待支付
     */
    WAIT("WAIT","待支付"),

    /**
     * 订单支付成功
     */
    SUCCESS("SUCCESS","交易成功"),

    /**
     * 订单关闭
     * 向上游下单异常时关闭订单
     * 上游回调关闭订单
     * 超时未支付
     * 执行退款操作后
     */
    CLOSED("CLOSED","交易关闭"),

//    /**
//     * 交易结束
//     * 无法退款
//     */
//    FINISHED("FINISHED","交易结束"),

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

    TradeOrderStatusEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
