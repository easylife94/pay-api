package com.pay.api.core.service.impl;

import com.pay.api.client.constants.ApiPayGatewayParamsErrorEnum;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.client.utils.SignUtils;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.IPayApiGatewayService;
import com.pay.api.core.utils.SpringContextUtil;
import com.pay.center.client.dto.service.MemberDTO;
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

    @Override
    public ApiPayGatewayParamsErrorEnum publicParamsCheck(ApiPayDTO apiPayDTO) {
        //todo 公共参数校验
        return null;
    }

    @Override
    public Boolean verifySign(ApiPayDTO apiPayDTO, MemberDTO memberDTO) {
        String signType = apiPayDTO.getSignType().toUpperCase();
        switch (signType) {
            case "RSA":
                return SignUtils.verifyRsa(apiPayDTO.getContent(), memberDTO.getMemberPubKey(), apiPayDTO.getSign());
            case "RSA2":
                return SignUtils.verifyRsa2(apiPayDTO.getContent(), memberDTO.getMemberPubKey(), apiPayDTO.getSign());
            default:
                logger.error("不支持签名算法类型:{}", signType);
                return Boolean.FALSE;
        }
    }

    @Override
    public void sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, MemberDTO memberDTO) {

        //TODO 参数生成签名
    }

    @Override
    public String encrypt(String content, MemberDTO memberDTO) {
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
