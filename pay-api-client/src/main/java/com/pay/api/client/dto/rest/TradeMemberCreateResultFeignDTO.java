package com.pay.api.client.dto.rest;

/**
 * 交易会员创建结果DTO
 *
 * @author chenwei
 * @date 2019/3/2 16:45
 */
public class TradeMemberCreateResultFeignDTO extends BaseResultFeignDTO {

    private static final long serialVersionUID = 8128506897639352393L;

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 系统生成公钥
     */
    private String sysPubKey;

    /**
     * 系统生成私钥
     */
    private String sysPriKey;

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getSysPubKey() {
        return sysPubKey;
    }

    public void setSysPubKey(String sysPubKey) {
        this.sysPubKey = sysPubKey;
    }

    public String getSysPriKey() {
        return sysPriKey;
    }

    public void setSysPriKey(String sysPriKey) {
        this.sysPriKey = sysPriKey;
    }
}
