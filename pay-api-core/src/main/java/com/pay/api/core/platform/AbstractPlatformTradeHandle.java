package com.pay.api.core.platform;

import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.constants.TradeSysConfigKeyEnum;
import com.pay.api.client.dto.*;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.core.service.IAliPayService;
import com.pay.api.core.service.ITradeSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

/**
 * 抽象平台交易处理器
 *
 * @author chenwei
 * @date 2019/2/14 14:37
 */
public abstract class AbstractPlatformTradeHandle implements IPlatformTradeHandle, IDefrayalChannelTrade, IDefrayalTypeTrade {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPlatformTradeHandle.class);

    @Value("${trade.pre-order-url}")
    private String preOrderUrl;

    private final ITradeSysConfigService tradeSysConfigService;
    private final IAliPayService aliPayService;

    protected AbstractPlatformTradeHandle(ITradeSysConfigService tradeSysConfigService, IAliPayService aliPayService) {
        this.tradeSysConfigService = tradeSysConfigService;
        this.aliPayService = aliPayService;
    }

    @Override
    final public TradeHandleResultDTO trade(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO,
                                            TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalChannel()) {
            case ALI:
                return aliPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case WECHAT:
                return wechatPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JD:
                return jdPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case BAIDU:
                return baiduPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case QQ:
                return qqPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case UNION:
                return unionPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case OFFLINE:
                return offlinePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
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
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            default:
                throw new PayApiException("支付宝不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO wechatPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            default:
                throw new PayApiException("微信不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO qqPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            default:
                throw new PayApiException("qq钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO jdPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            default:
                throw new PayApiException("京东钱包不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO unionPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case B2B:
                return b2bPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case B2C_DEBIT:
                return b2cDebitPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case B2C_CREDIT:
                return b2cCreditPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            default:
                throw new PayApiException("银联不支持" + tradeHandleDTO.getDefrayalType() + "支付方式");
        }
    }

    @Override
    final public TradeHandleResultDTO baiduPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        switch (tradeHandleDTO.getDefrayalType()) {
            case SCAN:
                return scanPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case NATIVE:
                return nativePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case APP:
                return appPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case H5:
                return h5Payment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case JSAPI:
                return jsapiPayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
            case SOLIDCODE:
                return solidCodePayment(tradeHandleDTO, tradeChannelConfigDTO, tradeMerchantConfigDTO);
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
     * 系统预下单
     * 跳转系统预下单地址
     *
     * @param tradeHandleDTO 交易处理参数
     * @return 返回交易处理结果
     */
    protected TradeHandleResultDTO preOrderPayment(TradeHandleDTO tradeHandleDTO) {
        String base64 = Base64.getEncoder().encodeToString(tradeHandleDTO.getSysOrderNumber().getBytes());
        TradeSysConfigDTO config = tradeSysConfigService.getConfig(TradeSysConfigKeyEnum.PRE_ORDER_HOST.name());
        if (config == null) {
            logger.error("系统预下单失败，交易系统配置:{}不存在，", TradeSysConfigKeyEnum.PRE_ORDER_HOST, tradeHandleDTO.getSysOrderNumber());
            return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "交易系统配置key:" + TradeSysConfigKeyEnum.PRE_ORDER_HOST + "不存在", null, null);
        }
        return new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, null, preOrderUrl + base64, null);
    }


    /**
     * 原生jsapi预下单
     * 需要先获取用户
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置
     * @param tradeMerchantConfigDTO 交易商户配置
     * @return 交易处理结果
     */
    protected TradeHandleResultDTO primaryJsapiPreOrder(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        try {
            String preOrderUrl = null;
            switch (tradeHandleDTO.getDefrayalChannel()) {
                case ALI:
                    AliPayAuthDTO aliPayAuthDTO = new AliPayAuthDTO();
                    AliConfigDTO aliConfigDTO = new AliConfigDTO();
                    preOrderUrl = aliPayService.buildAuthUrl(aliPayAuthDTO, aliConfigDTO);
                    break;
                case WECHAT:
                    //todo 微信待实现
                    break;
                default:
                    logger.error("原生jsapi预下单失败，不支持支付渠道：{},订单号：{}", tradeHandleDTO.getDefrayalChannel(), tradeHandleDTO.getSysOrderNumber());
                    return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "原生jsapi预下单失败，不支持支付渠道：" + tradeHandleDTO.getDefrayalChannel(), null, null);
            }
            return new TradeHandleResultDTO(TradeHandleStatusEnum.SUCCESS, null, preOrderUrl, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("原生jsapi预下单异常，异常信息：{},订单号：{}", e.getMessage(), tradeHandleDTO.getSysOrderNumber());
            return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "原生jsapi预下单异常", null, null);
        }
    }
}
