package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
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
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO scanPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持反扫支付方式");
    }

    /**
     * 正扫支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO nativePayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持正扫支付方式");
    }

    /**
     * app支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO appPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持app支付方式");
    }

    /**
     * h5支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO h5Payment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持h5支付方式");
    }

    /**
     * jsapi支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO jsapiPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持jsapi支付方式");
    }

    /**
     * 固码支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO solidCodePayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持固码支付方式");
    }

    /**
     * b2b支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO b2bPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持b2b支付方式");
    }

    /**
     * b2c储蓄卡支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO b2cDebitPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持b2c储蓄卡支付方式");
    }

    /**
     * b2c信用支付方式
     *
     * @param tradeHandleDTO
     * @return
     */
    default TradeHandleResultDTO b2cCreditPayment(TradeHandleDTO tradeHandleDTO) {
        throw new PayApiException("不支持b2c信用卡支付方式");
    }
}
