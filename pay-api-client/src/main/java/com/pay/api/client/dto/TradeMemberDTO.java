package com.pay.api.client.dto;

import lombok.Data;

/**
 * @author chenwei
 * @date 2019-03-02
 */
@Data
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
