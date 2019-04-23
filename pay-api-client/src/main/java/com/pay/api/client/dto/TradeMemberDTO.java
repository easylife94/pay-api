package com.pay.api.client.dto;

import com.pay.api.client.utils.FeeUtils;
import lombok.Data;

import java.util.List;

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
    private List<FeeUtils.Fee> serviceFees;


    public TradeMemberDTO(Long memberId, String memberNumber, String memberName, String memberPubKey,
                          String sysPubKey, String sysPriKey, Long agentId, String agentNumber,
                          String agentName, String agentLevel,List<FeeUtils.Fee> serviceFees) {
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
        this.serviceFees = serviceFees;
    }
}
