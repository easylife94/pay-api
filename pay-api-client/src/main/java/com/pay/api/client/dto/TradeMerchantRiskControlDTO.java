package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 交易风控商户DTO
 *
 * @author chenwei
 * @date 2019/2/1 11:43
 */
public class TradeMerchantRiskControlDTO {

    /**
     * 商户号
     */
    private String merchantNumber;

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public TradeMerchantRiskControlDTO(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeMerchantRiskControlDTO.class.getSimpleName() + "{", "}")
                .add("merchantNumber=" + merchantNumber)
                .toString();
    }
}
