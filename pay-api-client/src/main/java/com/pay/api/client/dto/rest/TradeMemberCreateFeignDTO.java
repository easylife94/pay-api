package com.pay.api.client.dto.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * 交易会员创建DTO
 *
 * @author chenwei
 * @date 2019/3/2 16:45
 */
@Data
public class TradeMemberCreateFeignDTO implements Serializable {

    private static final long serialVersionUID = -7407570831436820634L;

    /**
     * 会员编号
     */
    private Long memberId;

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 会员公钥
     */
    private String memberPubKey;

    /**
     * 代理商id
     */
    private Long agentId;

    /**
     * 代理商编号
     */
    private String agentNumber;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 代理商级别枚举
     */
    private String agentLevel;

    public TradeMemberCreateFeignDTO(Long memberId, String memberNumber, String memberName, String memberPubKey,
                                     Long agentId, String agentNumber, String agentName, String agentLevel) {
        this.memberId = memberId;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.memberPubKey = memberPubKey;
        this.agentId = agentId;
        this.agentNumber = agentNumber;
        this.agentName = agentName;
        this.agentLevel = agentLevel;
    }
}
