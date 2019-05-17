package com.pay.api.client.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenwei
 */
@Data
public class TradeMemberDO implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private Long memberId;

    private String memberNumber;

    private String memberName;

    private String status;

    private Long agentId;

    private String agentNumber;

    private String agentName;

    private String agentLevel;

    private String sysPubKey;

    private String sysPriKey;

    private String memberPubKey;
}