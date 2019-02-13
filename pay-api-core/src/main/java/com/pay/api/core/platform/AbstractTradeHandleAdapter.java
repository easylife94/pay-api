package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.exception.TradeMethodNotImplementException;

/**
 * 抽象交易处理器适配器
 *
 * @author chenwei
 * @date 2019-02-04
 */
public abstract class AbstractTradeHandleAdapter implements ITradeHandle {

    @Override
    public TradeHandleResultDTO aliPayScanPayment(TradeHandleDTO tradeHandleDTO) throws TradeMethodNotImplementException {
        throw new TradeMethodNotImplementException("未实现支付宝反扫");
    }

    @Override
    public TradeHandleResultDTO aliPayNativePayment(TradeHandleDTO tradeHandleDTO) throws TradeMethodNotImplementException {
        throw new TradeMethodNotImplementException("未实现支付宝正扫");
    }

    @Override
    public TradeHandleResultDTO aliPayJsapiPayment(TradeHandleDTO tradeHandleDTO) {
        return null;
    }

    @Override
    public TradeHandleResultDTO aliPayH5Payment(TradeHandleDTO tradeHandleDTO) {
        return null;
    }

    @Override
    public TradeHandleDTO aliPaySdkPayment(TradeHandleDTO tradeHandleDTO) {
        return null;
    }
}
