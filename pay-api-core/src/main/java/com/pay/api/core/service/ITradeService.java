package com.pay.api.core.service;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;

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


}
