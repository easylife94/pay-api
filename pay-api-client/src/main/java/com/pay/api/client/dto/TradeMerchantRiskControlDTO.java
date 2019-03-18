package com.pay.api.client.dto;

import lombok.Data;

/**
 * 交易风控商户DTO
 *
 * @author chenwei
 * @date 2019/2/1 11:43
 */
@Data
public class TradeMerchantRiskControlDTO {

    /**
     * 商户号
     */
    private String merchantNumber;

    public TradeMerchantRiskControlDTO(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }
}
