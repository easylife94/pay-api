package com.pay.api.core.platform.test;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.core.platform.AbstractPlatformTradeHandle;
import com.pay.api.core.service.IAliPayService;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeSysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019/2/11 16:32
 */
@Component("TEST")
public class TestPlatformTradeHandle extends AbstractPlatformTradeHandle {

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public TestPlatformTradeHandle(ITradeSysConfigService tradeSysConfigService, IAliPayService aliPayService, ITradeOrderService tradeOrderService) {
        super(tradeSysConfigService, aliPayService);
        this.tradeOrderService = tradeOrderService;
    }

    @Override
    public TradeHandleResultDTO scanPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                            TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO nativePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                              TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO appPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO h5Payment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                          TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    /**
     * 原生jsapi支付方式，由页面使用参数调用js唤起
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    @Override
    public TradeHandleResultDTO jsapiPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                             TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return preOrderPayment(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO solidCodePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                 TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return preOrderPayment(tradeHandleDTO);
    }

    @Override
    public TradeHandleResultDTO b2bPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO b2cDebitPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO b2cCreditPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                                 TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        return testTrade(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
    }

    @Override
    public TradeHandleResultDTO preOrderTrade(TradeHandleDTO tradeHandleDTO) {
        return super.preOrderTrade(tradeHandleDTO);
    }

    private TradeHandleResultDTO testTrade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, "", "测试下单成功", System.currentTimeMillis() + "");
        return tradeHandleResultDTO;
    }

}
