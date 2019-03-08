package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 支付宝授权配置参数
 *
 * @author chenwei
 * @date 2019/3/8 11:19
 */
public class AliConfigDTO {

    /**
     * 支付宝应用appId
     */
    private String appId;

    /**
     * 支付宝应用授权回调地址
     */
    private String authRedirectUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAuthRedirectUrl() {
        return authRedirectUrl;
    }

    public void setAuthRedirectUrl(String authRedirectUrl) {
        this.authRedirectUrl = authRedirectUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                AliConfigDTO.class.getSimpleName() + "{", "}")
                .add("appId=" + appId)
                .add("authRedirectUrl=" + authRedirectUrl)
                .toString();
    }
}
