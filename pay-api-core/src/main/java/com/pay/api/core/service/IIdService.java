package com.pay.api.core.service;

/**
 * Id服务接口
 *
 * @author chenwei
 * @date 2019/1/30 13:57
 */
public interface IIdService {

    /**
     * 创建全局唯一long类型id
     *
     * @return
     */
    Long generateId();

    /**
     * 创建全局唯一交易订单号
     *
     * @return
     */
    String generateTradeOrderNumber();
}
