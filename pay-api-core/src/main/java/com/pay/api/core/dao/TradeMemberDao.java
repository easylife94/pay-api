package com.pay.api.core.dao;

import com.pay.api.client.model.TradeMemberDO;
import com.pay.common.client.dto.QueryBase;
import com.pay.common.core.dao.IBaseDao;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author chenwei
 */
public interface TradeMemberDao extends IBaseDao<TradeMemberDO> {

    /**
     * 更新记录，不设置值为null的字段
     *
     * @param record
     * @return
     */
    @Override
    @CacheEvict(value = "tradeMemberCache", allEntries = true)
    int updateByPrimaryKeySelective(TradeMemberDO record);

    /**
     * 更新记录
     *
     * @param record
     * @return
     */
    @Override
    @CacheEvict(value = "tradeMemberCache", allEntries = true)
    int updateByPrimaryKey(TradeMemberDO record);

    /**
     * 根据会员编号查询记录
     *
     * @param memberNumber
     * @return
     */
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