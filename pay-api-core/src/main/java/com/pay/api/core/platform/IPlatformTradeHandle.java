package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;

/**
 * 平台交易接口
 *
 * @author chenwei
 * @date 2019/2/11 16:32
 */
public interface IPlatformTradeHandle {

    /**
     * 下单接口
     *
     * @param tradeHandleDTO 交易处理参数
     * @return 返回处理结果
     */
    TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO);

    /**
     * 预下单交易
     * 预下单地址是固定的
     *
     * @param tradeHandleDTO 交易处理参数
     * @return 返沪处理结果
     */
    TradeHandleResultDTO preOrderTrade(TradeHandleDTO tradeHandleDTO);
}
