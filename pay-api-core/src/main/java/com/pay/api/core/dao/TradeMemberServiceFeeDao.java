package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMemberServiceFeeDO;
import com.pay.common.core.dao.IBaseDao;

import java.util.List;

/**
 * @author chenwei
 * @date 2019/4/23 10:57
 */
public interface TradeMemberServiceFeeDao extends IBaseDao<TradeMemberServiceFeeDO> {

    List<TradeMemberServiceFeeDO> selectByMemberId(Long memberId);
}
