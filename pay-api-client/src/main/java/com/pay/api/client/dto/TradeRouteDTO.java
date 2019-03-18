package com.pay.api.client.dto;

import lombok.Data;

/**
 * 交易路由参数DTO
 *
 * @author chenwei
 * @date 2019/1/17 15:25
 */
@Data
public class TradeRouteDTO {

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 平台编号
     */
    private String platformNumber;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 商户编号
     */
    private String merchantNumber;

    /**
     * 支付渠道
     */
    private String defrayalChannel;

    /**
     * 支付方式
     */
    private String defrayalType;

    public TradeRouteDTO(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                         String defrayalChannel, String defrayalType) {
        this.memberNumber = memberNumber;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.merchantNumber = merchantNumber;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
    }
}
