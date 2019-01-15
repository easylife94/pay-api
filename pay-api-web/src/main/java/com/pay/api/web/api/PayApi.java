package com.pay.api.web.api;

import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.manager.PayApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付接口
 *
 * @author chenwei
 * @date 2019/1/14 10:57
 */
@RestController
@RequestMapping("/api/pay")
public class PayApi {

    @Autowired
    private PayApiGatewayService payApiGatewayService;

    @RequestMapping("/gateway")
    public ApiPayResultDTO gateway(ApiPayDTO apiPayDTO){
        ApiPayResultDTO apiPayResultDTO = new ApiPayResultDTO();

        //1.验证签名
        //1.1获取会员信息
        //1.2参数签名



        return apiPayResultDTO;
    }
}
