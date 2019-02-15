package com.pay.api.core.platform;

import com.pay.api.client.dto.JsapiPreorderDTO;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;

/**
 * 预下单接口
 *
 * @author chenwei
 * @date 2019/2/15 14:04
 */
public interface IPreorder {

    /**
     * 支付宝jsapi预下单
     *
     * @param tradeHandleDTO
     * @param jsapiPreorderDTO
     * @return
     */
    TradeHandleResultDTO aliJsapiPreorder(TradeHandleDTO tradeHandleDTO, JsapiPreorderDTO jsapiPreorderDTO);

    /**
     * 微信jsapi预下单
     *
     * @param tradeHandleDTO
     * @param jsapiPreorderDTO
     * @return
     */
    TradeHandleResultDTO wechatJsapiPreorder(TradeHandleDTO tradeHandleDTO, JsapiPreorderDTO jsapiPreorderDTO);
}
