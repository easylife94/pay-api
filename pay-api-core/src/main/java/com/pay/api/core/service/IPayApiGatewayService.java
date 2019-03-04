package com.pay.api.core.service;

import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.ApiPayParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayResultDTO;
import com.pay.api.client.dto.TradeMemberDTO;
import com.pay.api.core.method.IPayApiMethod;

/**
 * 支付网关服务接口
 *
 * @author chenwei
 * @date 2019/1/14 11:30
 */
public interface IPayApiGatewayService {


    /**
     * 公共参数校验
     *
     * @param apiPayDTO 请求参数
     * @return 不为空
     */
    ApiPayParamsCheckResultDTO publicParamsCheck(ApiPayDTO apiPayDTO);

    /**
     * 校验签名
     *
     * @param apiPayDTO 请求参数
     * @param memberDTO 会员
     * @return 当且仅当验签通过返回false
     */
    Boolean verifySign(ApiPayDTO apiPayDTO, TradeMemberDTO memberDTO);

    /**
     * 加密
     *
     * @param apiPayDTO       请求参数
     * @param apiPayResultDTO 返回结果参数
     * @param memberDTO       会员
     * @return 当且仅当加密成功返回true
     */
    Boolean encrypt(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, TradeMemberDTO memberDTO);

    /**
     * 结果签名，apiPayResultDTO的sign字段会被设置
     *
     * @param apiPayDTO       请求参数
     * @param apiPayResultDTO 返回结果参数
     * @param memberDTO       会员
     * @return 当且仅当签名成功返回true
     */
    Boolean sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, TradeMemberDTO memberDTO);


    /**
     * 路由方法
     *
     * @param method 方法实例
     * @return 找不到mehotd方法则返回null
     */
    IPayApiMethod route(String method);

}
