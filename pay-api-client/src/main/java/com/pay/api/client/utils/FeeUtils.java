package com.pay.api.client.utils;

import com.pay.api.client.constants.FeeTypeEnum;
import com.pay.api.client.exception.PayApiException;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenwei
 * @date 2019/4/23 10:00
 */
public class FeeUtils {

    @Data
    public static class Fee {
        private FeeTypeEnum feeType;
        private BigDecimal feeValue;

        /**
         * 最小金额，0无效
         */
        private BigDecimal min;

        /**
         * 最大金额，0无效
         */
        private BigDecimal max;

        public Fee(FeeTypeEnum feeType, BigDecimal feeValue, BigDecimal min, BigDecimal max) {
            this.feeType = feeType;
            this.feeValue = feeValue;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * 获取费用
     *
     * @param fees   费用对象
     * @param amount 交易金额
     * @return 返回费用金额
     */
    public static BigDecimal fee(List<Fee> fees, BigDecimal amount) {
        for (Fee fee : fees) {
            boolean inRange = amount.compareTo(fee.min) >= 0;
            //max 大于0才生效
            if (fee.max.compareTo(BigDecimal.ZERO) > 0) {
                inRange = inRange && (fee.max.compareTo(amount) >= 0);
            }
            if (inRange) {
                switch (fee.getFeeType()) {
                    case PERCENTAGE:
                        return amount.multiply(fee.feeValue).divide(new BigDecimal(100)).setScale(2,   BigDecimal.ROUND_HALF_UP);
                    case FIXED:
                        return fee.feeValue;
                    default:
                        throw new PayApiException("无效费用类型：" + fee.getFeeType());
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Fee> fees = new ArrayList<>();
        fees.add(new Fee(FeeTypeEnum.FIXED, new BigDecimal("0.01"), new BigDecimal("0.00"), new BigDecimal("1.00")));
        fees.add(new Fee(FeeTypeEnum.PERCENTAGE, new BigDecimal("1.00"), new BigDecimal("1.01"), new BigDecimal("10.00")));
        System.out.println(fee(fees, new BigDecimal("1.01")));
    }
}
