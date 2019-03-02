package com.pay.api.client.dto.rest;

import java.io.Serializable;

/**
 * 交易会员创建DTO
 *
 * @author chenwei
 * @date 2019/3/2 16:45
 */
public class TradeMemberCreateFeignDTO implements Serializable {

    private static final long serialVersionUID = -7407570831436820634L;

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 会员公钥
     */
    private String memberPubKey;

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

    public TradeMemberCreateFeignDTO(String memberNumber, String memberPubKey) {
        this.memberNumber = memberNumber;
        this.memberPubKey = memberPubKey;
    }
}
