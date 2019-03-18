package com.pay.api.client.dto.async;

import lombok.Data;

import java.io.Serializable;
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

    public TradeCreateMessageDTO(String sysOrderNumber, Long tradeTimestamp) {
        this.sysOrderNumber = sysOrderNumber;
        this.tradeTimestamp = tradeTimestamp;
    }

}
