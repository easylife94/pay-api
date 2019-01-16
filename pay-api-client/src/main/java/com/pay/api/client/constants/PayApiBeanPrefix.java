package com.pay.api.client.constants;

/**
 * spring bean 前缀
 * 避免冲突
 *
 * @author chenwei
 * @date 2019/1/16 13:56
 */
public class PayApiBeanPrefix {

    /**
     * 顶级命名空间
     */
    private static final String BASE = "PAY-API.";

    /**
     * 方法bean的命名空间
     */
    public static final String METHOD = BASE + "METHOD.";
}
