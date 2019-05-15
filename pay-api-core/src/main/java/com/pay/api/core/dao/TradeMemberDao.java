package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMemberDO;
import com.pay.common.client.dto.QueryBase;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TradeMemberDao {

    int deleteByPrimaryKey(Long id);

    int insert(TradeMemberDO record);

    int insertSelective(TradeMemberDO record);

    TradeMemberDO selectByPrimaryKey(Long id);

    @CacheEvict(value = "tradeMemberCache", allEntries = true)
    int updateByPrimaryKeySelective(TradeMemberDO record);

    @CacheEvict(value = "tradeMemberCache", allEntries = true)
    int updateByPrimaryKey(TradeMemberDO record);

    @Cacheable(value = "tradeMemberCache", key = "'TradeMemberDO:memberNumber:'.concat(#root.args[0])")
    TradeMemberDO selectByMemberNumber(String memberNumber);

    /**
     * 根据查询条件统计记录数
     *
     * @param query 查询条件
     * @return 总记录数
     */
    Long countByQuery(QueryBase query);

    /**
     * 根据查询条件查询
     *
     * @param query 查询条件
     * @return 记录
     */
    List<TradeMemberDO> selectByQuery(QueryBase query);

}