package com.pay.api.core.platform;

import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.exception.TradeMethodNotImplementException;

/**
 * 交易处理器
 *
 * @author chenwei
 * @date 2019-02-04
 */
public interface ITradeHandle {


    /**
     * 支付宝反扫
     *
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleResultDTO aliPayScanPayment(TradeHandleDTO tradeHandleDTO) throws TradeMethodNotImplementException;

    /**
     * 支付宝正扫
     *
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleResultDTO aliPayNativePayment(TradeHandleDTO tradeHandleDTO) throws TradeMethodNotImplementException;

    /**
     * 支付宝jsapi
     *
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleResultDTO aliPayJsapiPayment(TradeHandleDTO tradeHandleDTO);

    /**
     * 支付宝h5
     *
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleResultDTO aliPayH5Payment(TradeHandleDTO tradeHandleDTO);

    /**
     * 支付宝sdk
     *
     * @param tradeHandleDTO
     * @return
     */
    TradeHandleDTO aliPaySdkPayment(TradeHandleDTO tradeHandleDTO);



}
