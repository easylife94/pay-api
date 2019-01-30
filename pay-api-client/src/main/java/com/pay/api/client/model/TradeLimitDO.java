package com.pay.api.client.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class TradeLimitDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private String ownType;

    private Long ownId;

    private String ownNumber;

    private String ownName;

    private String defrayalType;

    private BigDecimal singleTradeAmountMin;

    private BigDecimal singleTradeAmountMax;

    private BigDecimal dayTradeAmountMax;

    private BigDecimal monthTradeAmountMax;

    private Integer secondTradeInterval;

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

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType == null ? null : ownType.trim();
    }

    public Long getOwnId() {
        return ownId;
    }

    public void setOwnId(Long ownId) {
        this.ownId = ownId;
    }

    public String getOwnNumber() {
        return ownNumber;
    }

    public void setOwnNumber(String ownNumber) {
        this.ownNumber = ownNumber == null ? null : ownNumber.trim();
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName == null ? null : ownName.trim();
    }

    public String getDefrayalType() {
        return defrayalType;
    }

    public void setDefrayalType(String defrayalType) {
        this.defrayalType = defrayalType == null ? null : defrayalType.trim();
    }

    public BigDecimal getSingleTradeAmountMin() {
        return singleTradeAmountMin;
    }

    public void setSingleTradeAmountMin(BigDecimal singleTradeAmountMin) {
        this.singleTradeAmountMin = singleTradeAmountMin;
    }

    public BigDecimal getSingleTradeAmountMax() {
        return singleTradeAmountMax;
    }

    public void setSingleTradeAmountMax(BigDecimal singleTradeAmountMax) {
        this.singleTradeAmountMax = singleTradeAmountMax;
    }

    public BigDecimal getDayTradeAmountMax() {
        return dayTradeAmountMax;
    }

    public void setDayTradeAmountMax(BigDecimal dayTradeAmountMax) {
        this.dayTradeAmountMax = dayTradeAmountMax;
    }

    public BigDecimal getMonthTradeAmountMax() {
        return monthTradeAmountMax;
    }

    public void setMonthTradeAmountMax(BigDecimal monthTradeAmountMax) {
        this.monthTradeAmountMax = monthTradeAmountMax;
    }

    public Integer getSecondTradeInterval() {
        return secondTradeInterval;
    }

    public void setSecondTradeInterval(Integer secondTradeInterval) {
        this.secondTradeInterval = secondTradeInterval;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                TradeLimitDO.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("gmtCreate=" + gmtCreate)
                .add("gmtUpdate=" + gmtUpdate)
                .add("isDeleted=" + isDeleted)
                .add("ownType=" + ownType)
                .add("ownId=" + ownId)
                .add("ownNumber=" + ownNumber)
                .add("ownName=" + ownName)
                .add("defrayalType=" + defrayalType)
                .add("singleTradeAmountMin=" + singleTradeAmountMin)
                .add("singleTradeAmountMax=" + singleTradeAmountMax)
                .add("dayTradeAmountMax=" + dayTradeAmountMax)
                .add("monthTradeAmountMax=" + monthTradeAmountMax)
                .add("secondTradeInterval=" + secondTradeInterval)
                .toString();
    }
}