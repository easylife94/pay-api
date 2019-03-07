package com.pay.api.client.dto.rest;

import java.util.StringJoiner;

/**
 * 交易会员更新结果DTO
 *
 * @author chenwei
 * @date 2019/3/2 17:06
 */
public class TradeMemberUpdateResultFeignDTO extends BaseResultFeignDTO {
    private static final long serialVersionUID = 7134278332743603747L;

    /**
     * 会员编号
     */
    private String memberNumber;

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                super.toString() +
                        TradeMemberUpdateResultFeignDTO.class.getSimpleName() + "{", "}")
                .add("memberNumber=" + memberNumber)
                .toString();
    }
}
