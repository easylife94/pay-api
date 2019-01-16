package com.pay.api.core.method.impl;

import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.api.ApiPayMethodResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 统一支付方法
 *
 * @author chenwei
 * @date 2019/1/16 13:55
 */
@Component(PayApiBeanPrefix.METHOD + ApiPayMethodNames.UNIFIED_PAY)
public class PayApiMethodUnifiedPay implements IPayApiMethod {

    private static Logger logger = LoggerFactory.getLogger(PayApiMethodUnifiedPay.class);

    @Override
    public ApiPayMethodResultDTO operate(String content) {
        ApiPayMethodResultDTO apiPayMethodResultDTO = new ApiPayMethodResultDTO();

        //TODO 统一支付方法
        logger.info("统一支付");

        return apiPayMethodResultDTO;
    }
}
