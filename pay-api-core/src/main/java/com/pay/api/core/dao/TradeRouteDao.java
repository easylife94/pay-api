package com.pay.api.core.dao;

import com.pay.api.client.dto.mapper.MemberTradeRouteDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.common.core.dao.IBaseDao;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chenwei
 */
public interface TradeRouteDao extends IBaseDao<TradeRouteDO> {

    /**
     * 轮询会员交易路由
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
    MemberTradeRouteDTO roundRobinMemberTradeRoute(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                                                   String defrayalChannel, String defrayalType, BigDecimal tradeAmount);

    /**
     * 查询路由
     *
     * @param memberNumber
     * @param platformNumber
     * @param channelNumber
     * @param merchantNumber
     * @param defrayalChannel
     * @param defrayalType
     * @return
     */
    List<TradeRouteDO> selectTradeRoute(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                                        String defrayalChannel, String defrayalType);

    /**
     * 批量插入
     *
     * @param records
     * @return
     */
    int insertBatch(List<TradeRouteDO> records);

    /**
     * 更新路由禁用、交易限额、交易风控等状态
     *
     * @param memberNumber
     * @param platformNumber
     * @param channelNumber
     * @param merchantNumber
     * @param defrayalChannel
     * @param defrayalType
     * @param status
     * @param tradeLimit
     * @param tradeLimitDate
     * @param tradeRisk
     * @param tradeRiskDate
     * @return
     */
    int updateTradeRouteStatus(String memberNumber, String platformNumber, String channelNumber, String merchantNumber,
                               String defrayalChannel, String defrayalType, Boolean status, Boolean tradeLimit,
                               Date tradeLimitDate, Boolean tradeRisk, Date tradeRiskDate);


    /**
     * 更新交易路由交易时间
     *
     * @param id        交易路由id
     * @param timestamp 时间戳
     * @return
     */
    int updateTradeRoute(Long id, Long timestamp);
}