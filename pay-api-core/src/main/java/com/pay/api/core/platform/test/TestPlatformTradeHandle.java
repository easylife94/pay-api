package com.pay.api.core.platform.test;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.PlatformTradeNotifyResultEnum;
import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.constants.TradeOrderNotifyStatusEnum;
import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.api.client.dto.*;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.client.utils.SignUtils;
import com.pay.api.core.platform.AbstractPlatformTradeHandle;
import com.pay.api.core.platform.test.dto.TestNotifyDTO;
import com.pay.api.core.service.IAliPayService;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeSysConfigService;
import com.pay.api.core.service.IWechatService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenwei
 * @date 2019/2/11 16:32
 */
@Component("TEST")
public class TestPlatformTradeHandle extends AbstractPlatformTradeHandle {

    private static final Logger logger = LoggerFactory.getLogger(TestPlatformTradeHandle.class);

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public TestPlatformTradeHandle(ITradeSysConfigService tradeSysConfigService, IAliPayService aliPayService, ITradeOrderService tradeOrderService,
                                   IWechatService wechatService) {
        super(tradeSysConfigService, aliPayService, wechatService);
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
    public TradeHandleResultDTO primaryJsapiPayment(TradeHandleDTO tradeHandleDTO, PrimaryJsapiPaymentDTO primaryJsapiPaymentDTO) {
        TradeHandleResultDTO resultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, "", "", "");
        //todo jsapi原生支付

        return resultDTO;
    }

    private TradeHandleResultDTO testTrade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                           TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, "", "测试下单成功", System.currentTimeMillis() + "");
        return tradeHandleResultDTO;
    }

    @Override
    public TradeNotifyResultDTO notify(TradeChannelConfigDTO channelConfigDTO, String body, HttpServletRequest request) {
        TradeNotifyResultDTO tradeNotifyResultDTO = new TradeNotifyResultDTO();
        tradeNotifyResultDTO.setResult(PlatformTradeNotifyResultEnum.FAIL);
        try {
            TestNotifyDTO testNotifyDTO = JSONObject.toJavaObject(JSONObject.parseObject(body), TestNotifyDTO.class);
            if (StringUtils.equals(testNotifyDTO.getTradeStatus(), "SUCCESS")) {
                //rsa验证签名
                String sign = testNotifyDTO.getSign();
                testNotifyDTO.setSign(null);
                if (SignUtils.verifyRsa(SignUtils.str(testNotifyDTO), channelConfigDTO.getPlatformPubKey(), sign)) {
                    tradeNotifyResultDTO.setResult(PlatformTradeNotifyResultEnum.SUCCESS);
                    tradeNotifyResultDTO.setPayTime(DateUtils.parse(testNotifyDTO.getPayTime(), DateUtils.FORMAT_YYYYMMDDHHMMSS_1));
                    tradeNotifyResultDTO.setSysOrderNumber(testNotifyDTO.getOutTradeNo());
                    tradeNotifyResultDTO.setPlatformOrderNumber(testNotifyDTO.getTradeNo());
                    tradeNotifyResultDTO.setTradeStatus(TradeOrderStatusEnum.SUCCESS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("TEST平台回调处理异常，ERROR:{}", e.getMessage());
        }
        return tradeNotifyResultDTO;
    }
}
