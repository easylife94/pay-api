package com.pay.api.client.dto;

/**
 * @author chenwei
 * @date 2019-03-02
 */
public class TradeMemberDTO {

    private Long memberId;
    private String memberNumber;
    private String memberName;
    private String memberPubKey;
    private String sysPubKey;
    private String sysPriKey;
    private Long agentId;
    private String agentNumber;
    private String agentName;
    private String agentLevel;

    public String getMemberNumber() {
        return this.memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPubKey() {
        return this.memberPubKey;
    }

    public void setMemberPubKey(String memberPubKey) {
        this.memberPubKey = memberPubKey;
    }

    public String getSysPubKey() {
        return this.sysPubKey;
    }

    public void setSysPubKey(String sysPubKey) {
        this.sysPubKey = sysPubKey;
    }

    public String getSysPriKey() {
        return this.sysPriKey;
    }

    public void setSysPriKey(String sysPriKey) {
        this.sysPriKey = sysPriKey;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentNumber() {
        return this.agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentLevel() {
        return this.agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public TradeMemberDTO(Long memberId, String memberNumber, String memberName, String memberPubKey,
                          String sysPubKey, String sysPriKey, Long agentId, String agentNumber,
                          String agentName, String agentLevel) {
        this.memberId = memberId;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.memberPubKey = memberPubKey;
        this.sysPubKey = sysPubKey;
        this.sysPriKey = sysPriKey;
        this.agentId = agentId;
        this.agentNumber = agentNumber;
        this.agentName = agentName;
        this.agentLevel = agentLevel;
    }
}
