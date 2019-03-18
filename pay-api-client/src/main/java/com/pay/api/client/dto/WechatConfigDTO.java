package com.pay.api.client.dto;

import lombok.Data;

/**
 * 微信配置DTO
 *
 * @author chenwei
 * @date 2019/3/12 15:02
 */
@Data
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

    public WechatConfigDTO(String appId, String secretKey, String authRedirectUrl) {
        this.appId = appId;
        this.secretKey = secretKey;
        this.authRedirectUrl = authRedirectUrl;
    }
}
