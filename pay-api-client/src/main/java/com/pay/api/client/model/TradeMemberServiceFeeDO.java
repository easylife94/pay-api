package com.pay.api.client.model;

import com.pay.common.client.model.AbstractBaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenwei
 * @date 2019/4/23 10:54
 */
@Data
public class TradeMemberServiceFeeDO extends AbstractBaseDO {

    private Long memberId;

    private BigDecimal tradeAmountMin;

    private BigDecimal tradeAmountMax;

    private String serviceFeeType;

    private BigDecimal serviceFee;

    public TradeMemberServiceFeeDO(Long id) {
        super(id);
    }

    public TradeMemberServiceFeeDO() {
    }
}
