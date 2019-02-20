package com.pay.api.core.dao;

import com.pay.api.client.model.TradeOrderDO;

public interface TradeOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrderDO record);

    int insertSelective(TradeOrderDO record);

    TradeOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrderDO record);

    int updateByPrimaryKey(TradeOrderDO record);

    /**
     * 根据系统订单号查询
     *
     * @param sysOrderNumber 系统订单号
     * @return
     */
    TradeOrderDO selectBySysOrderNumber(String sysOrderNumber);

    /**
     * 根据会员订单号查询
     *
     * @param memberNumber      会员编号
     * @param memberOrderNumber 会员订单号
     * @return
     */
    TradeOrderDO selectByMemberOrderNumber(String memberNumber, String memberOrderNumber);
}