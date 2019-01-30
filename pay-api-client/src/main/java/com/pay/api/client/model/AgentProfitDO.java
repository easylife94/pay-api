package com.pay.api.client.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class AgentProfitDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private Long agentId;

    private String agentNumber;

    private String agentName;

    private Integer agentLevel;

    private String sysOrderNumber;

    private BigDecimal profit;

    private BigDecimal tradeAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
        this.agentNumber = agentNumber == null ? null : agentNumber.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber == null ? null : sysOrderNumber.trim();
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                AgentProfitDO.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("gmtCreate=" + gmtCreate)
                .add("gmtUpdate=" + gmtUpdate)
                .add("isDeleted=" + isDeleted)
                .add("agentId=" + agentId)
                .add("agentNumber=" + agentNumber)
                .add("agentName=" + agentName)
                .add("agentLevel=" + agentLevel)
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("profit=" + profit)
                .add("tradeAmount=" + tradeAmount)
                .toString();
    }
}