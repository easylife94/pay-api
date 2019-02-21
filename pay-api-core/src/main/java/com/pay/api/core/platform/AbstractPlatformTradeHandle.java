package com.pay.api.core.platform;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.core.platform.test.TestPlatformTradeHandle;
import com.pay.api.core.service.impl.TradeOrderServiceImpl;
import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Base64;

/**
 * 抽象平台交易处理器
 *
 * @author chenwei
 * @date 2019/2/14 14:37
 */
public abstract class AbstractPlatformTradeHandle implements IPlatformTradeHandle, IDefrayalChannelTrade, IDefrayalTypeTrade {

    @Value("${trade.pre-order-url}")
    private String preOrderUrl;

    @Override
    final public TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalChannel()) {
            case ALI:
                return aliPayment(tradeHandleDTO);
            case WECHAT:
                return wechatPayment(tradeHandleDTO);
            case JD:
                return jdPayment(tradeHandleDTO);
            case BAIDU:
                return baiduPayment(tradeHandleDTO);
            case QQ:
                return qqPayment(tradeHandleDTO);
            case UNION:
                return unionPayment(tradeHandleDTO);
            case OFFLINE:
                return offlinePayment(tradeHandleDTO);
            default:
                throw new PayApiException("不支持支付渠道");
        }
    }

    @Override
    final public TradeHandleResultDTO aliPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            default:
                throw new PayApiException("支付宝不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO wechatPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            default:
                throw new PayApiException("微信不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO qqPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            default:
                throw new PayApiException("qq钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO jdPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            default:
                throw new PayApiException("京东钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO unionPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            case B2B:
                return b2bPayment(tradeHandleDTO);
            case B2C_DEBIT:
                return b2cDebitPayment(tradeHandleDTO);
            case B2C_CREDIT:
                return b2cCreditPayment(tradeHandleDTO);
            default:
                throw new PayApiException("银联不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO baiduPayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO);
            case APP:
                return appPayment(tradeHandleDTO);
            case H5:
                return h5Payment(tradeHandleDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO);
            default:
                throw new PayApiException("百度钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO offlinePayment(TradeHandleDTO tradeHandleDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            default:
                throw new PayApiException("线下不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    /**
     * 原生jsapi预下单
     *
     * @param tradeHandleDTO
     * @return
     */
    public TradeHandleResultDTO jsapiPreorder(TradeHandleDTO tradeHandleDTO) {
        String base64 = Base64.getEncoder().encodeToString(tradeHandleDTO.getSysOrderNumber().getBytes());
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, null, preOrderUrl + base64, null);
        return tradeHandleResultDTO;
    }
}
