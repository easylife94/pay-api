package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;

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
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return 返回处理结果
     */
    TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO);

    /**
     * 预下单交易
     *
     * @param tradeHandleDTO 交易处理参数
     * @return 返回处理结果
     */
    TradeHandleResultDTO preOrderTrade(TradeHandleDTO tradeHandleDTO);
}
