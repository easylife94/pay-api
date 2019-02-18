package com.pay.api.core.dao;

import com.pay.api.client.model.TradeRouteDO;

import java.math.BigDecimal;
import java.util.List;

public interface TradeRouteDao {
    int deleteByPrimaryKey(Long id);

    int insert(TradeRouteDO record);

    int insertSelective(TradeRouteDO record);

    TradeRouteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeRouteDO record);

    int updateByPrimaryKey(TradeRouteDO record);

    /**
     * 查询可交易路由
     *
     * @param memberNumber    会员编号
     * @param platformNumber  平台编号
     * @param channelNumber   通道编号
     * @param merchantNumber  商户编号
     * @param defrayalChannel 支付渠道
     * @param defrayalType    支付方式
     * @param tradeAmount     交易金额
     * @return
     */
    List<TradeRouteDO> selectMemberTradableRoute(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                                                 String defrayalChannel, String defrayalType,
                                                 BigDecimal tradeAmount);
}