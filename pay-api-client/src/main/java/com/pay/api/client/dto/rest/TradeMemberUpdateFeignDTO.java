package com.pay.api.client.dto.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 交易会员更新参数DTO
 *
 * @author chenwei
 * @date 2019/3/2 17:06
 */
@Data
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
}
