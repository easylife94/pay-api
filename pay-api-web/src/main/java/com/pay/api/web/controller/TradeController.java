package com.pay.api.web.controller;

import com.pay.api.core.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

/**
 * @author chenwei
 * @date 2019/2/21 15:38
 */
@Controller
@RequestMapping("trade")
public class TradeController {

    private final ITradeService tradeService;

    @Autowired
    public TradeController(ITradeService tradeService) {
        this.tradeService = tradeService;
    }


    @RequestMapping("/preOrder/{code}")
    public String preOrder(@PathVariable("code") String code) {
        byte[] decode = Base64.getDecoder().decode(code.getBytes());
        String sysOrderNumber = new String(decode);




        return null;
    }





}
