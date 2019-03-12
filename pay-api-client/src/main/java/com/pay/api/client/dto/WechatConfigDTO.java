package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 微信配置DTO
 *
 * @author chenwei
 * @date 2019/3/12 15:02
 */
public class WechatConfigDTO {

    /**
     * app id
     */
    private String appId;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 授权回调地址
     */
    private String authRedirectUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAuthRedirectUrl() {
        return authRedirectUrl;
    }

    public void setAuthRedirectUrl(String authRedirectUrl) {
        this.authRedirectUrl = authRedirectUrl;
    }

    public WechatConfigDTO(String appId, String secretKey, String authRedirectUrl) {
        this.appId = appId;
        this.secretKey = secretKey;
        this.authRedirectUrl = authRedirectUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                WechatConfigDTO.class.getSimpleName() + "{", "}")
                .add("appId=" + appId)
                .add("secretKey=" + secretKey)
                .add("authRedirectUrl=" + authRedirectUrl)
                .toString();
    }
}
