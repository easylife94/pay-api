package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.client.exception.PayApiException;

/**
 * 支付方式交易接口
 *
 * @author chenwei
 * @date 2019/2/15 13:44
 */
interface IDefrayalTypeTrade {

    /**
     * 反扫支付方式
     * 使用场景：扫客户app付款码发起支付。需上传用户付款码参数。
     * 返回内容：无
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO scanPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持反扫支付方式");
    }

    /**
     * 正扫支付方式
     * 使用场景：在支付app内使用扫一扫功能扫码发起支付
     * 返回内容：链接地址
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO nativePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持正扫支付方式");
    }

    /**
     * todo 返回内容说明
     * app支付方式
     * 使用场景：在非支付app(微信、支付宝)内调用sdk可以唤起支付app进行支付
     * 返回内容:待定
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO appPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持app支付方式");
    }

    /**
     * h5支付方式
     * 使用场景：浏览器或非支付app(微信，支付宝)的内置浏览器打开可以唤起支付app进行支付
     * 返回内容:链接地址
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO h5Payment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持h5支付方式");
    }

    /**
     * jsapi支付方式
     * 使用场景：链接地址转为二维码可以在app中识别相册打开并发起支付
     * 返回内容：链接地址
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO jsapiPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持jsapi支付方式");
    }

    /**
     * 固码支付方式
     * 使用场景：在app中的固码页面输入金额发起支付
     * 返回内容：链接地址
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO solidCodePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持固码支付方式");
    }

    /**
     * b2b支付方式
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO b2bPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持b2b支付方式");
    }

    /**
     * b2c储蓄卡支付方式
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO b2cDebitPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持b2c储蓄卡支付方式");
    }

    /**
     * b2c信用卡支付方式
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO b2cCreditPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持b2c信用卡支付方式");
    }
}
