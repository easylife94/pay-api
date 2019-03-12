package com.pay.api.core.service;

import com.alipay.api.AlipayApiException;
import com.pay.api.client.dto.AliConfigDTO;
import com.pay.api.client.dto.OAuthSuccessDTO;

import java.io.UnsupportedEncodingException;

/**
 * 支付宝接口服务
 *
 * @author chenwei
 * @date 2019/3/7 17:33
 */
public interface IAliPayService {


    /**
     * 获取应用授权token
     *
     * @param appAuthCode     应用授权码
     * @param appId           应用Id
     * @param privateKey      应用私钥
     * @param alipayPublicKey 支付宝公钥
     * @return 应用授权token
     * @throws AlipayApiException 支付宝异常
     */
    String getAppAuthToken(String appAuthCode, String appId, String privateKey, String alipayPublicKey) throws AlipayApiException;

    /**
     * 获取用户userId
     *
     * @param authCode        授权码
     * @param appId           应用Id
     * @param privateKey      应用私钥
     * @param alipayPublicKey 支付宝公钥
     * @return 支付宝userId
     * @throws AlipayApiException 支付宝异常
     */
    String getUserId(String authCode, String appId, String privateKey, String alipayPublicKey) throws AlipayApiException;

    /**
     * 构建支付宝获取授权请求地址
     *
     * @param oAuthSuccessDTO 系统内部传递参数
     * @param aliConfigDTO  支付宝配置参数
     * @return 支付宝获取授权请求地址
     */
    String buildAuthUrl(OAuthSuccessDTO oAuthSuccessDTO, AliConfigDTO aliConfigDTO) throws UnsupportedEncodingException;
}
