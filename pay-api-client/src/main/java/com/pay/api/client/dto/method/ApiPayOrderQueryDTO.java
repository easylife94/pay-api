package com.pay.api.client.dto.method;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author chenwei
 * @date 2019/2/20 10:19
 */
public class ApiPayOrderQueryDTO implements Serializable {

    private static final long serialVersionUID = 7582250076282801746L;

    /**
     * 系统订单号
     */
    private String sysOrderNumber;

    /**
     * 会员订单号
     */
    private String memberOrderNumber;

    public ApiPayOrderQueryDTO(String sysOrderNumber, String memberOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
        this.memberOrderNumber = memberOrderNumber;
    }

    public String getSysOrderNumber() {
        return sysOrderNumber;
    }

    public void setSysOrderNumber(String sysOrderNumber) {
        this.sysOrderNumber = sysOrderNumber;
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
                ApiPayOrderQueryDTO.class.getSimpleName() + "{", "}")
                .add("sysOrderNumber=" + sysOrderNumber)
                .add("memberOrderNumber=" + memberOrderNumber)
                .toString();
    }
}
