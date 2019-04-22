package com.pay.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.PayResultPageStatusEnum;
import com.pay.api.client.constants.PlatformTradeNotifyResultEnum;
import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.*;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.platform.IPlatformTradeHandle;
import com.pay.api.core.service.ITradeChannelConfigService;
import com.pay.api.core.service.ITradeMerchantConfigService;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeService;
import com.pay.api.core.utils.SpringContextUtil;
import com.pay.common.client.constants.DefrayalChannelEnum;
import com.pay.common.client.constants.DefrayalTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * @author chenwei
 * @date 2019/2/21 15:38
 */
@Slf4j
@Controller
@RequestMapping("trade")
public class TradeController {

    private final ITradeService tradeService;
    private final ITradeOrderService tradeOrderService;
    private final ITradeChannelConfigService tradeChannelConfigService;
    private final ITradeMerchantConfigService tradeMerchantConfigService;

    @Autowired
    public TradeController(ITradeService tradeService, ITradeOrderService tradeOrderService, ITradeChannelConfigService tradeChannelConfigService,
                           ITradeMerchantConfigService tradeMerchantConfigService) {
        this.tradeService = tradeService;
        this.tradeOrderService = tradeOrderService;
        this.tradeChannelConfigService = tradeChannelConfigService;
        this.tradeMerchantConfigService = tradeMerchantConfigService;
    }


    /**
     * 预下单跳转地址
     * 下一跳：
     * 1.跳转微信或支付宝授权接口 -> 支付宝和微信统一授权回调地址 -> 实际支付页面（原生jsapi支付）
     * 2.通道自定义跳转地址（自定义后续发起支付逻辑）
     *
     * @param code 预下单内容（base64编码）
     * @return 重定下地址或支付结果页面（异常情况）
     */
    @RequestMapping("/preOrder/{code}")
    public Object preOrder(@PathVariable("code") String code) {
        byte[] decode = Base64.getDecoder().decode(code.getBytes());
        String sysOrderNumber = new String(decode);
        TradeOrderDO oneOrder = tradeOrderService.findOneOrder(sysOrderNumber, null, null);
        if (oneOrder == null) {
            //订单不存在
            return payResult(PayResultPageStatusEnum.FAIL, "订单不存在");
        } else {
            Object bean = SpringContextUtil.getBean(oneOrder.getPlatformMapped());
            if (bean instanceof IPlatformTradeHandle) {
                IPlatformTradeHandle platformTrade = (IPlatformTradeHandle) bean;
                DefrayalChannelEnum defrayalChannel = DefrayalChannelEnum.valueOf(oneOrder.getDefrayalChannel());
                DefrayalTypeEnum defrayalType = DefrayalTypeEnum.valueOf(oneOrder.getDefrayalType());
                TradeHandleDTO tradeHandleDTO = new TradeHandleDTO(oneOrder.getPlatformMapped(), oneOrder.getChannelNumber(), oneOrder.getMerchantNumber(),
                        oneOrder.getSysOrderNumber(), oneOrder.getTradeAmount(), defrayalChannel, defrayalType, null);
                TradeChannelConfigDTO channelConfig = tradeChannelConfigService.getChannelConfig(oneOrder.getChannelNumber());
                TradeMerchantConfigDTO merchantConfig = tradeMerchantConfigService.getMerchantConfig(oneOrder.getMerchantNumber());
                TradeHandleResultDTO tradeHandleResultDTO = platformTrade.preOrder(tradeHandleDTO, channelConfig, merchantConfig);
                switch (tradeHandleResultDTO.getStatus()) {
                    case SUCCESS:
                        return "redirect:" + tradeHandleResultDTO.getContent();
                    case UNKNOWN:
                        return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
                    case RISK:
                        //关闭订单，触发风控
                        return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
                    case ERROR:
                        //关闭订单，触发预警
                        return payResult(PayResultPageStatusEnum.COSED, "系统异常");
                    default:
                        log.error("预下单异常，未知预下单处理返回状态枚举类：" + tradeHandleResultDTO.getStatus());
                        return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
                }
            } else {
                log.error("预下单异常，找不到平台交易处理器：{},系统订单号：{}", oneOrder.getPlatformMapped(), oneOrder.getSysOrderNumber());
                return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
            }
        }
    }

    /**
     * jsapi 支付方式
     *
     * @param businessData 业务数据
     * @param userId       支付宝userId
     * @param openId       微信open id
     * @return jsapi支付页面
     */
    @RequestMapping("/jsapiPayment")
    public Object jsapiPayment(String businessData, String userId, String openId) {
        try {
            byte[] decode = Base64.getDecoder().decode(businessData.getBytes());
            businessData = new String(decode);
            JsapiPaymenDTO jsapiPaymenDTO = JSONObject.parseObject(businessData).toJavaObject(JsapiPaymenDTO.class);
            TradeOrderDO oneOrder = tradeOrderService.findOneOrder(jsapiPaymenDTO.getSysOrderNumber(), null, null);
            DefrayalChannelEnum defrayalChannel = DefrayalChannelEnum.valueOf(oneOrder.getDefrayalChannel());
            TradeHandleDTO tradeHandleDTO = new TradeHandleDTO(oneOrder.getPlatformMapped(), oneOrder.getChannelNumber(), oneOrder.getMerchantNumber(),
                    oneOrder.getSysOrderNumber(), oneOrder.getTradeAmount(), defrayalChannel, DefrayalTypeEnum.JSAPI, null);
            TradeChannelConfigDTO channelConfig = tradeChannelConfigService.getChannelConfig(oneOrder.getChannelNumber());
            PrimaryJsapiPaymentDTO primaryJsapiPaymentDTO;
            switch (defrayalChannel) {
                case ALI:
                    primaryJsapiPaymentDTO = new PrimaryJsapiPaymentDTO(userId);
                    break;
                case WECHAT:
                    primaryJsapiPaymentDTO = new PrimaryJsapiPaymentDTO(channelConfig.getWechatAppId(), openId);
                    break;
                default:
                    throw new PayApiException("jsapi支付不支持支付渠道：" + defrayalChannel);
            }


            Object bean = SpringContextUtil.getBean(tradeHandleDTO.getPlatformMapped());
            if (bean instanceof IPlatformTradeHandle) {
                IPlatformTradeHandle platformTrade = (IPlatformTradeHandle) bean;
                TradeHandleResultDTO tradeHandleResultDTO = platformTrade.primaryJsapiPayment(tradeHandleDTO, primaryJsapiPaymentDTO);
                switch (tradeHandleResultDTO.getStatus()) {
                    case SUCCESS:
                        break;
                    case ERROR:
                        break;
                    case RISK:
                        break;
                    case UNKNOWN:
                        break;
                    default:

                }
                return null;
            } else {
                return new TradeHandleResultDTO(TradeHandleStatusEnum.ERROR, "找不到平台交易处理器：" + tradeHandleDTO.getPlatformMapped(), null, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("jsapi支付异常，提示信息：{}", e.getMessage());
        }
        return null;
    }


    private ModelAndView payResult(PayResultPageStatusEnum status, String info) {
        ModelAndView view = new ModelAndView("/trade/preOrderResult");
        view.addObject("status", status.name());
        view.addObject("info", info);
        return view;
    }


    /**
     * 根据上游回调地址要求匹配回调地址。
     *
     * @param platformMapped 平台标识
     * @param channelNumber  通道编号
     * @return
     */
    @RequestMapping("/notify/{platformMapped}/{channelNumber}")
    public Object notify(@PathVariable(value = "platformMapped") String platformMapped, @PathVariable(value = "channelNumber", required = false) String channelNumber,
                         @RequestBody(required = false) String body, HttpServletRequest request) {
        try {
            TradeChannelConfigDTO channelConfig = tradeChannelConfigService.getChannelConfig(channelNumber);
            Object bean = SpringContextUtil.getBean(platformMapped);
            if (bean instanceof IPlatformTradeHandle) {
                IPlatformTradeHandle platformTrade = (IPlatformTradeHandle) bean;
                TradeNotifyResultDTO notifyResultDTO = platformTrade.notify(channelConfig, body, request);
                if (PlatformTradeNotifyResultEnum.SUCCESS.equals(notifyResultDTO.getResult())) {
                    TradeCompleteResultDTO completeResult = tradeService.complete(new TradeCompleteDTO(notifyResultDTO.getSysOrderNumber(),
                            notifyResultDTO.getPlatformOrderNumber(), notifyResultDTO.getSourceOrderNumber(), notifyResultDTO.getPayTime(), new Date()));
                    if (!completeResult.getSuccess()) {
                        log.error("完成订单失败:{}", notifyResultDTO);
                        return "完成订单失败";
                    }
                }
                return notifyResultDTO.getResponseContent();
            } else {
                log.error("找不到平台交易处理器channelMapped:{},body:{}", platformMapped, body);
                return "回调异常，详情请看日志";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上游回调异常,channelMapped:{},channelNumber:{},body:{}", platformMapped, channelNumber, body);
            return "回调异常，详情请看日志";
        }
    }


}
