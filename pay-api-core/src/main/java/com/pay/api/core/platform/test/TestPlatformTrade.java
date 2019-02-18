package com.pay.api.core.platform.test;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.core.platform.AbstractPlatformTrade;
import com.pay.api.core.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019/2/11 16:32
 */
@Component("TEST")
public class TestPlatformTrade extends AbstractPlatformTrade {

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public TestPlatformTrade(ITradeOrderService tradeOrderService) {
        this.tradeOrderService = tradeOrderService;
    }

    @Override
    public TradeHandleResultDTO scanPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO nativePayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO appPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO h5Payment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO jsapiPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO solidCodePayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO b2bPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO b2cDebitPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO b2cCreditPayment(TradeHandleDTO tradeHandleDTO) {
        return testTrade(tradeHandleDTO);
    }

    private TradeHandleResultDTO testTrade(TradeHandleDTO tradeHandleDTO){
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS,"","",System.currentTimeMillis() + "");
        return tradeHandleResultDTO;
    }

}
