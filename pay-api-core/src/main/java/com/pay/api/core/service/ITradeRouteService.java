package com.pay.api.core.service;

import com.pay.api.client.constants.TradeRouteRuleEnum;
import com.pay.api.client.dto.TradeRouteCreateDTO;
import com.pay.api.client.dto.TradeRouteDTO;
import com.pay.api.client.dto.TradeRouteMerchantDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 交易路由服务
 *
 * @author chenwei
 * @date 2019/1/17 15:02
 */
public interface ITradeRouteService {


    /**
     * 新增路由
     *
     * @param tradeRouteCreateDTOS 新增路由
     * @return 但且仅当新增成功返回true
     */
    Boolean create(List<TradeRouteCreateDTO> tradeRouteCreateDTOS);

    /**
     * 交易路由
     *
     * @param tradeRouteDTO 路由参数
     * @param tradeRouteRule 交易路由规则
     * @param tradeAmount   交易金额
     * @return
     */
    TradeRouteMerchantDTO route(TradeRouteDTO tradeRouteDTO, TradeRouteRuleEnum tradeRouteRule, BigDecimal tradeAmount);

    /**
     * 路由风控
     *
     * @param tradeRouteDTO 路由参数
     * @param status        风控状态，缺省值true
     * @param expireTime    风控有效期，status为true时生效
     */
    void risk(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime);

    /**
     * 路由限额
     *
     * @param tradeRouteDTO 路由参数
     * @param status        限额状态，缺省值true
     * @param expireTime    限额时间，status为true时生效
     */
    void limit(TradeRouteDTO tradeRouteDTO, Boolean status, Date expireTime);

    /**
     * 路由禁用
     *
     * @param tradeRouteDTO 路由参数
     * @param status        禁用状态，缺省值true
     */
    void disable(TradeRouteDTO tradeRouteDTO, Boolean status);

    /**
     * 路由限额更新。空值不更新
     *
     * @param tradeRouteDTO        路由参数
     * @param singleTradeAmountMin 单笔最小交易金额
     * @param singleTradeAmountMax 单笔最大交易金额
     * @param dayTradeAmountMax    日最大交易金额
     * @param monthTradeAmountMax  月最大交易金额
     * @return
     */
    Boolean limitUpdate(TradeRouteDTO tradeRouteDTO, BigDecimal singleTradeAmountMin, BigDecimal singleTradeAmountMax,
                        BigDecimal dayTradeAmountMax, BigDecimal monthTradeAmountMax);
}
