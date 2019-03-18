package com.pay.api.client.dto;

import lombok.Data;

/**
 * 支付宝授权配置参数
 *
 * @author chenwei
 * @date 2019/3/8 11:19
 */

@Data
public class AliConfigDTO {

    /**
     * 支付宝应用appId
     */
    private String appId;

    /**
     * 支付宝应用授权回调地址
     */
    private String authRedirectUrl;

    public AliConfigDTO(String appId, String authRedirectUrl) {
        this.appId = appId;
        this.authRedirectUrl = authRedirectUrl;
    }
}
