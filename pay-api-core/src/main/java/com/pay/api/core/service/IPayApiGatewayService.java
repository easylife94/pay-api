package com.pay.api.core.service;

import com.pay.api.client.constants.ApiPayGatewayParamsErrorEnum;
import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.center.client.dto.service.MemberDTO;

/**
 * 支付网关管理器
 *
 * @author chenwei
 * @date 2019/1/14 11:30
 */
public interface IPayApiGatewayService {


    /**
     * 公共参数校验
     *
     * @param apiPayDTO 请求参数
     * @return 校验通过返回null
     */
    ApiPayGatewayParamsErrorEnum publicParamsCheck(ApiPayDTO apiPayDTO);

    /**
     * 校验签名
     *
     * @param apiPayDTO 请求参数
     * @param memberDTO 会员
     * @return 当且仅当验签通过返回false
     */
    Boolean verifySign(ApiPayDTO apiPayDTO, MemberDTO memberDTO);

    /**
     * 加密
     *
     * @param content   待加密内容
     * @param memberDTO 会员
     * @return 返回加密后内容
     */
    String encrypt(String content, MemberDTO memberDTO);

    /**
     * 结果签名，apiPayResultDTO的sign字段会被设置
     *
     * @param apiPayDTO       请求参数
     * @param apiPayResultDTO 返回结果参数
     * @param memberDTO       会员
     */
    void sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, MemberDTO memberDTO);


    /**
     * 路由方法
     *
     * @param method 方法实例
     * @return 找不到mehotd方法则返回null
     */
    IPayApiMethod route(String method);

}
