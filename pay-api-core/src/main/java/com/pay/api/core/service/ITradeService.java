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

    TradeHandleResultDTO tradeHandle(TradeHandleDTO tradeHandleDTO);
}
