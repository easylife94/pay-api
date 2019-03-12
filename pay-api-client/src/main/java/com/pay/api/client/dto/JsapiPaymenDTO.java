package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * jsapi支付DTO
 *
 * @author chenwei
 * @date 2019/3/12 16:40
 */
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

    @Override
    public String toString() {
        return new StringJoiner(", ",
                JsapiPaymenDTO.class.getSimpleName() + "{", "}")
                .add("sysOrderNumber=" + sysOrderNumber)
                .toString();
    }
}
