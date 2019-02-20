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
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO);


}
