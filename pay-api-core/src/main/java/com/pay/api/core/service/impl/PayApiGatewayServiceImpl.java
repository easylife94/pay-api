package com.pay.api.core.service.impl;

import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.IPayApiGatewayService;
import com.pay.api.core.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/1/14 11:32
 */
@Service
public class PayApiGatewayServiceImpl implements IPayApiGatewayService {

    private static final Logger logger = LoggerFactory.getLogger(PayApiGatewayServiceImpl.class);

    /**
     * 验签规则，去掉sign参数，调用统一签名方法获取签名
     *
     * @param apiPayDTO
     * @return
     */
    @Override
    public Boolean verifySign(ApiPayDTO apiPayDTO) {

        //TODO 参数验证签名

        return Boolean.TRUE;
    }

    @Override
    public void sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO) {

        //TODO 参数生成签名
    }

    @Override
    public String encrypt(String content) {
        return null;
    }

    @Override
    public IPayApiMethod route(String method) {
        StringBuilder methodBeanId = new StringBuilder();
        methodBeanId.append(PayApiBeanPrefix.METHOD);
        methodBeanId.append(method);

        Object methodBean = SpringContextUtil.getBean(methodBeanId.toString());
        if (methodBean instanceof IPayApiMethod) {
            return (IPayApiMethod) methodBean;
        } else {
            logger.error("路由方法失败，找不到方法Bean id:{}", methodBeanId);
        }
        return null;
    }
}
