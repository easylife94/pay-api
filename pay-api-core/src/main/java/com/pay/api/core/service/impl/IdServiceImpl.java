package com.pay.api.core.service.impl;

import com.pay.api.client.utils.SnowflakeIdWorker;
import com.pay.api.core.service.IIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author chenwei
 * @date 2019/2/19 16:18
 */
@Service
public class IdServiceImpl implements IIdService {

    private SnowflakeIdWorker snowflakeIdWorker;

    @Value("${order.trade.number-prefix}")
    private String orderNumberPreFix;


    @Autowired
    public IdServiceImpl(@Value("${snowflake.data-center-id}") Long dataCenterId) {
        snowflakeIdWorker = new SnowflakeIdWorker(1L,dataCenterId);
    }

    @Override
    public Long generateId() {
        return snowflakeIdWorker.nextId();
    }

    @Override
    public String generateTradeOrderNumber() {
        return new StringBuilder(orderNumberPreFix).append(snowflakeIdWorker.nextId()).toString();
    }
}
