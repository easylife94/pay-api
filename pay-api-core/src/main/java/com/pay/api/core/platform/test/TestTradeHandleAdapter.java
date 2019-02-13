package com.pay.api.core.platform.test;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.exception.TradeMethodNotImplementException;
import com.pay.api.client.stereotype.TradeHandleAdapterComponent;
import com.pay.api.core.platform.AbstractTradeHandleAdapter;
import com.pay.center.client.constants.PlatformMappedEnum;

/**
 * @author chenwei
 * @date 2019-02-04
 */
@TradeHandleAdapterComponent(PlatformMappedEnum.TEST)
public class TestTradeHandleAdapter extends AbstractTradeHandleAdapter {
    @Override
    public TradeHandleResultDTO aliPayScanPayment(TradeHandleDTO tradeHandleDTO) {
        TradeHandleResultDTO tradeHandleResultDTO = null;
        //todo 支付宝正扫
        return tradeHandleResultDTO;
    }

    @Override
    public TradeHandleResultDTO aliPayNativePayment(TradeHandleDTO tradeHandleDTO) throws TradeMethodNotImplementException {
        return super.aliPayNativePayment(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO aliPayJsapiPayment(TradeHandleDTO tradeHandleDTO) {
        return super.aliPayJsapiPayment(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO aliPayH5Payment(TradeHandleDTO tradeHandleDTO) {
        return super.aliPayH5Payment(tradeHandleDTO);
    }

    @Override
    public TradeHandleDTO aliPaySdkPayment(TradeHandleDTO tradeHandleDTO) {
        return super.aliPaySdkPayment(tradeHandleDTO);
    }
}
