package com.pay.api.core.service;

import com.pay.api.client.dto.TradeMemberDTO;

/**
 * @author chenwei
 * @date 2019-03-02
 */
public interface ITradeMemberService {

    /**
     * 获取交易会员信息
     *
     * @param memberNumber 会员编号
     * @return 返回会员信息DTO
     */
    TradeMemberDTO getMember(String memberNumber);

}
