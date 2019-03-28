package com.pay.api.core.service;

import com.pay.api.client.dto.*;
import com.pay.api.client.model.TradeOrderDO;

/**
 * 交易服务接口
 *
 * @author chenwei
 * @date 2019-01-29
 */
public interface ITradeService {

    /**
     * 交易处理
     *
     * @param tradeHandleDTO 交易处理参数
     * @return 交易处理返回结果
     */
    TradeHandleResultDTO tradeHandle(TradeHandleDTO tradeHandleDTO);

    /**
     * 交易创建后执行
     *
     * @param tradeOrderDO             交易订单
     * @param tradeOrderCreateAfterDTO 交易创建后参数
     */
    void afterTradeCreate(TradeOrderDO tradeOrderDO, TradeCreateAfterDTO tradeOrderCreateAfterDTO);

    /**
     * 完成订单
     *
     * @param tradeCompleteDTO 完成订单参数
     * @return 完成订单结果
     */
    TradeCompleteResultDTO complete(TradeCompleteDTO tradeCompleteDTO);


    //订单关闭处理

    //订单退款处理

}
