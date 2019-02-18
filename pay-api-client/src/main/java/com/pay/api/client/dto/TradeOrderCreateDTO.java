package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/1/30 16:17
 */
public class TradeOrderCreateDTO {

    private Long memberId;
    private String memberNumber;
    private String memberName;
    private Long agentId;
    private String agentNumber;
    private String agentName;
    private String agentLevel;
    private String defrayalChannel;
    private String defrayalType;
    private String memberOrderNumber;

    public TradeOrderCreateDTO(Long memberId, String memberNumber, String memberName, Long agentId, String agentNumber,
                               String agentName, String agentLevel, String defrayalChannel, String defrayalType,
                               String memberOrderNumber) {
        this.memberId = memberId;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.agentId = agentId;
        this.agentNumber = agentNumber;
        this.agentName = agentName;
        this.agentLevel = agentLevel;
        this.defrayalChannel = defrayalChannel;
        this.defrayalType = defrayalType;
        this.memberOrderNumber = memberOrderNumber;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
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

    public String getDefrayalChannel() {
        return defrayalChannel;
    }

    public void setDefrayalChannel(String defrayalChannel) {
        this.defrayalChannel = defrayalChannel;
    }

    public String getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(String defrayalType) {
        this.defrayalType = defrayalType;
    }

    public String getMemberOrderNumber() {
        return memberOrderNumber;
    }

    public void setMemberOrderNumber(String memberOrderNumber) {
        this.memberOrderNumber = memberOrderNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeOrderCreateDTO.class.getSimpleName() + "{", "}")
                .add("memberId=" + memberId)
                .add("memberNumber=" + memberNumber)
                .add("memberName=" + memberName)
                .add("agentId=" + agentId)
                .add("agentNumber=" + agentNumber)
                .add("agentName=" + agentName)
                .add("agentLevel=" + agentLevel)
                .add("defrayalChannel=" + defrayalChannel)
                .add("defrayalType=" + defrayalType)
                .add("memberOrderNumber=" + memberOrderNumber)
                .toString();
    }
}
