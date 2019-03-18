package com.pay.api.client.dto;

import lombok.Data;

/**
 * jsapi支付DTO
 *
 * @author chenwei
 * @date 2019/3/12 16:40
 */
@Data
public class JsapiPaymenDTO {

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
    }

    public JsapiPaymenDTO(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
    }
}
