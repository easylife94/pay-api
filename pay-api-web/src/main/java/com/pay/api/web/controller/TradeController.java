package com.pay.api.web.controller;

import com.pay.api.client.constants.PayResultPageStatusEnum;
import com.pay.api.client.constants.TradeHandleStatusEnum;
import com.pay.api.client.dto.TradeHandleDTO;
import com.pay.api.client.dto.TradeHandleResultDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.platform.IPlatformTradeHandle;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.api.core.service.ITradeService;
import com.pay.api.core.utils.SpringContextUtil;
import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

/**
 * @author chenwei
 * @date 2019/2/21 15:38
 */
@Controller
@RequestMapping("trade")
public class TradeController {

    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    private final ITradeService tradeService;
    private final ITradeOrderService tradeOrderService;

    @Autowired
    public TradeController(ITradeService tradeService, ITradeOrderService tradeOrderService) {
        this.tradeService = tradeService;
        this.tradeOrderService = tradeOrderService;
    }


    /**
     * 预下单跳转地址
     * 下一跳：
     * 1.跳转微信或支付宝授权接口 -> 支付宝和微信统一授权回调地址 -> 实际支付页面（原生jsapi支付）
     * 2.上游跳转地址（直接可发起支付）
     * 3.通道自定义跳转地址（自定义后续发起支付逻辑）
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
                TradeHandleDTO tradeHandleDTO = new TradeHandleDTO(oneOrder.getPlatformMapped(), oneOrder.getSysOrderNumber(), oneOrder.getTradeAmount(), defrayalChannel, defrayalType);
                TradeHandleResultDTO tradeHandleResultDTO = platformTrade.preOrderTrade(tradeHandleDTO);
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
                        logger.error("预下单异常，未知预下单处理返回状态枚举类：" + tradeHandleResultDTO.getStatus());
                        return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
                }
            } else {
                logger.error("预下单异常，找不到平台交易处理器：{},系统订单号：{}", oneOrder.getPlatformMapped(), oneOrder.getSysOrderNumber());
                return payResult(PayResultPageStatusEnum.FAIL, "系统异常");
            }
        }
    }

    private ModelAndView payResult(PayResultPageStatusEnum status, String info) {
        ModelAndView view = new ModelAndView("/trade/preOrderResult");
        view.addObject("status", status.name());
        view.addObject("info", info);
        return view;
    }


}
