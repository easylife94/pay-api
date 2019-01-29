package com.pay.api.core.method.impl;

import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.center.client.dto.service.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 订单查询方法
 *
 * @author chenwei
 * @date 2019/1/16 16:52
 */
@Component(PayApiBeanPrefix.METHOD + ApiPayMethodNames.ODER_QUERY)
public class PayApiMethodOrderQuery implements IPayApiMethod {

    private static final Logger logger = LoggerFactory.getLogger(PayApiMethodOrderQuery.class);

    @Override
    public ApiPayMethodResultDTO<Object> operate(String content, MemberDTO memberDTO) {
        ApiPayMethodResultDTO<Object> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();

        //TODO 订单查询方法
        logger.info("查询订单");

        return apiPayMethodResultDTO;
    }
}
