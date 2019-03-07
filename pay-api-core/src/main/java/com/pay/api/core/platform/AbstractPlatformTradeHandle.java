package com.pay.api.core.platform;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.client.exception.PayApiException;
import org.springframework.beans.factory.annotation.Value;

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
    final public TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                            TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalChannel()) {
            case ALI:
                return aliPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case WECHAT:
                return wechatPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JD:
                return jdPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case BAIDU:
                return baiduPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case QQ:
                return qqPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case UNION:
                return unionPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case OFFLINE:
                return offlinePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("不支持支付渠道");
        }
    }

    @Override
    public TradeHandleResultDTO preOrderTrade(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支预下单支付方式");
    }

    @Override
    final public TradeHandleResultDTO aliPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("支付宝不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO wechatPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("微信不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO qqPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("qq钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO jdPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("京东钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO unionPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case B2B:
                return b2bPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case B2C_DEBIT:
                return b2cDebitPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case B2C_CREDIT:
                return b2cCreditPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("银联不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO baiduPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO,tradeChannelConfigDTO,tradeMerchantConfigDTO);
            default:
                throw new PayApiException("百度钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO offlinePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
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
    public TradeHandleResultDTO jsapiPreorder(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        String base64 = Base64.getEncoder().encodeToString(tradeHandleDTO.getSysOrderNumber().getBytes());
        TradeHandleResultDTO tradeHandleResultDTO = new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, null, preOrderUrl + base64, null);
        return tradeHandleResultDTO;
    }

}
