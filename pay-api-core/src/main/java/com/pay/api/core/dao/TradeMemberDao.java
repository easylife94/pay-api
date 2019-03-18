package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMemberDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface TradeMemberDao {

    int deleteByPrimaryKey(Long id);

    int insert(TradeMemberDO record);

    int insertSelective(TradeMemberDO record);

    TradeMemberDO selectByPrimaryKey(Long id);

    @CacheEvict(value = "tradeMemberCache", allEntries=true)
    int updateByPrimaryKeySelective(TradeMemberDO record);

    @CacheEvict(value = "tradeMemberCache", allEntries=true)
    int updateByPrimaryKey(TradeMemberDO record);

    @Cacheable(value = "tradeMemberCache", key = "'TradeMemberDO:memberNumber:'.concat(#root.args[0])")
    TradeMemberDO selectByMemberNumber(String memberNumber);
}