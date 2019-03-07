package com.pay.api.client.dto.rest;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 交易会员更新参数DTO
 *
 * @author chenwei
 * @date 2019/3/2 17:06
 */
public class TradeMemberUpdateFeignDTO implements Serializable {
    private static final long serialVersionUID = 6069173463664715819L;

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 会员公钥
     */
    private String memberPubKey;

    public TradeMemberUpdateFeignDTO(String memberNumber, String memberPubKey) {
        this.memberNumber = memberNumber;
        this.memberPubKey = memberPubKey;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberPubKey() {
        return memberPubKey;
    }

    public void setMemberPubKey(String memberPubKey) {
        this.memberPubKey = memberPubKey;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeMemberUpdateFeignDTO.class.getSimpleName() + "{", "}")
                .add("memberNumber=" + memberNumber)
                .add("memberPubKey=" + memberPubKey)
                .toString();
    }
}
