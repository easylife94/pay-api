package com.pay.api.core.service;

import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;

/**
 * 支付网关管理器
 *
 * @author chenwei
 * @date 2019/1/14 11:30
 */
public interface IPayApiGatewayService {


    /**
     * 校验签名
     *
     * @param apiPayDTO
     * @return
     */
    Boolean verifySign(ApiPayDTO apiPayDTO);

    /**
     * 加密
     *
     * @param content
     * @return 返回加密后
     */
    String encrypt(String content);

    /**
     * 结果签名，apiPayResultDTO的sign字段会被设置
     *
     * @param apiPayDTO
     * @param apiPayResultDTO
     */
    void sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO);


    /**
     * 路由方法
     *
     * @param method
     * @return 找不到mehotd方法则返回null
     */
    IPayApiMethod route(String method);

}
