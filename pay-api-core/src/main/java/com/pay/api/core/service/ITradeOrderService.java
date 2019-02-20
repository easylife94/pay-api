package com.pay.api.core.service;

import com.pay.api.client.dto.TradeOrderCreateDTO;
import com.pay.api.client.model.TradeOrderDO;

/**
 * 订单服务接口
 *
 * @author chenwei
 * @date 2019-01-29
 */
public interface ITradeOrderService {


    /**
     * 创建订单记录
     * 会员订单号不能重复
     *
     * @param tradeOrderCreateDTO 创建订单参数
     * @return 订单DO
     */
    TradeOrderDO createTradeOrder(TradeOrderCreateDTO tradeOrderCreateDTO);

    /**
     * 更新订单记录ØØ
     *
     * @param tradeOrderDO 订单
     * @return 保存成功返回true
     */
    boolean updateTradeOrder(TradeOrderDO tradeOrderDO);

}
