package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.client.exception.PayApiException;

/**
 * 支付渠道支付接口
 *
 * @author chenwei
 * @date 2019/2/15 13:39
 */
public interface IDefrayalChannelTrade {

    /**
     * 支付宝渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO aliPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持支付宝渠道支付");
    }

    /**
     * 微信渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO wechatPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持微信渠道支付");
    }

    /**
     * qq钱包渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO qqPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持qq钱包渠道支付");
    }

    /**
     * 京东钱包渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO jdPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持京东钱包渠道支付");
    }

    /**
     * 银联渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO unionPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持银联渠道支付");
    }

    /**
     * 百度钱包渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO baiduPayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持百度钱包渠道支付");
    }

    /**
     * 线下渠道支付
     *
     * @param tradeHandleDTO         交易处理参数
     * @param tradeChannelConfigDTO  交易通道配置参数
     * @param tradeMerchantConfigDTO 交易商户配置参数
     * @return
     */
    default TradeHandleResultDTO offlinePayment(TradeHandleDTO tradeHandleDTO, TradeChannelConfigDTO tradeChannelConfigDTO, TradeMerchantConfigDTO tradeMerchantConfigDTO) {
        throw new PayApiException("不支持线下渠道支付");
    }
}
