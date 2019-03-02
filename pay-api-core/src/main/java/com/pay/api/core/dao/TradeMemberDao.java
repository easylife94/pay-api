package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMemberDO;

public interface TradeMemberDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeMemberDO record);

    int insertSelective(TradeMemberDO record);

    TradeMemberDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeMemberDO record);

    int updateByPrimaryKey(TradeMemberDO record);

    TradeMemberDO selectByMemberNumber(String memberNumber);
}