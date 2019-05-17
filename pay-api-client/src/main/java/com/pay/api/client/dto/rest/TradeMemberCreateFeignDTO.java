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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

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
