package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易路由平台DTO
 *
 * @author chenwei
 * @date 2019/1/17 15:23
 */
public class TradeRoutePlatformDTO {

    private String platformNumber;

    @Override
    public String toString() {
        return new StringJoiner(", ", TradeRoutePlatformDTO.class.getSimpleName() + "[", "]")
                .add("platformNumber='" + platformNumber + "'")
                .toString();
    }
}
