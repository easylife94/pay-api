package com.pay.api.core.dao;

import com.pay.api.client.model.AgentProfitDO;

public interface AgentProfitDao {
    int deleteByPrimaryKey(Long id);

    int insert(AgentProfitDO record);

    int insertSelective(AgentProfitDO record);

    AgentProfitDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentProfitDO record);

    int updateByPrimaryKey(AgentProfitDO record);
}