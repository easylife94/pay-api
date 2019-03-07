package com.pay.api.core.platform.testerror;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.core.platform.AbstractPlatformTradeHandle;
import com.pay.api.core.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019/2/11 16:32
 */
@Component("TEST_ERROR")
public class TestErrorPlatformTradeHandle extends AbstractPlatformTradeHandle {

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public TestErrorPlatformTradeHandle(ITradeOrderService tradeOrderService) {
        this.tradeOrderService = tradeOrderService;
    }
    @Override
    public TradeHandleResultDTO scanPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                            TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO nativePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                              TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO appPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO h5Payment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                          TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO jsapiPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                             TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO solidCodePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                 TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO b2bPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO b2cDebitPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO b2cCreditPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                 TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
    }
    private TradeHandleResultDTO testTrade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "测试返回失败", null, null);
        return tradeHandleResultDTO;
    }

}
