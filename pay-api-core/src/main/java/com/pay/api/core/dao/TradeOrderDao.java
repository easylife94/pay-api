package com.pay.api.core.dao;

import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.api.client.model.TradeOrderDO;

import java.util.Date;

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

    /**
     * @param memberNumber
     * @param memberOrderNumber
     * @return
     */
    long countByMemberOrderNumber(String memberNumber, String memberOrderNumber);

    /**
     * 更新完成订单
     *
     * @param sysOrderNumber      订单号
     * @param platformOrderNumber 平台订单号
     * @param sourceOrderNumber   源订单号
     * @param payTime             支付时间
     * @param platformNotifyTime  平台回调时间
     * @param platformOrderTime   平台下单时间
     * @param tradeStatus         交易状态（成功）
     * @param gmtUpdate           更新时间
     * @return
     */
    int updateByCompleteTrade(String sysOrderNumber, String platformOrderNumber, String sourceOrderNumber,
                              Date payTime, Date platformNotifyTime, Date platformOrderTime, String tradeStatus, Date gmtUpdate);

}