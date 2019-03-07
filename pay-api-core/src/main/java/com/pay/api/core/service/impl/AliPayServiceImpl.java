package com.pay.api.core.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.core.service.IAliPayService;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/7 17:33
 */
@Service
public class AliPayServiceImpl implements IAliPayService {

    @Override
    public String getAppAuthToken(String appAuthCode, String appId, String privateKey, String alipayPublicKey) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey,
                "json", "utf-8", alipayPublicKey, "RSA2");

        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent("{" +
                "    \"grant_type\":\"authorization_code\"," +
                "    \"code\":\"" + appAuthCode + "\"" +
                "  }");
        AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
        if (!response.isSuccess()) {
            throw new PayApiException("支付宝应用授权token接口调用返回失败。msg：" + response.getMsg() + "，subMsg：" + response.getSubMsg());
        }
        return response.getAppAuthToken();
    }

    @Override
    public String getUserId(String authCode, String appId, String privateKey, String alipayPublicKey) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey,
                "json", "utf-8",alipayPublicKey, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
        if (!response.isSuccess()) {
            throw new PayApiException("支付宝用户userId接口调用返回失败。msg：" + response.getMsg() + "，subMsg：" + response.getSubMsg());
        }
        return response.getUserId();
    }
}
