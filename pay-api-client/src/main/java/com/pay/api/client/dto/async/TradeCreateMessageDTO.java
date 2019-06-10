package com.pay.api.client.dto.async;

import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.common.client.constants.DefrayalChannelEnum;
import com.pay.common.client.constants.DefrayalTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 交易创建消息DTO
 *
 * @author chenwei
 * @date 2019-02-20
 */
@Data
public class TradeCreateMessageDTO implements Serializable {

    private static final long serialVersionUID = 3384362006328832159L;

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 交易时间戳
     */
    private Long tradeTimestamp;

    private BigDecimal tradeAmount;

    private BigDecimal tradeServiceFee;

    private String tradeStatus;

    private String platformNumber;

    private String channelNumber;

    private String agentNumber;

    private String memberNumber;

    private String merchantNumber;

    private String defrayalChannel;

    private String defrayalType;


    public TradeCreateMessageDTO(String sysOrderNumber, Long tradeTimestamp, BigDecimal tradeAmount, BigDecimal tradeServiceFee,
                                 String tradeStatus, String platformNumber, String channelNumber, String agentNumber,
                                 String memberNumber, String merchantNumber, String defrayalChannel, String defrayalType) {
        this.sysOrderNumber = sysOrderNumber;
        this.tradeTimestamp = tradeTimestamp;
        this.tradeAmount = tradeAmount;
        this.tradeServiceFee = tradeServiceFee;
        this.tradeStatus = tradeStatus;
        this.platformNumber = platformNumber;
        this.channelNumber = channelNumber;
        this.agentNumber = agentNumber;
        this.memberNumber = memberNumber;
        this.merchantNumber = merchantNumber;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
    }
}
