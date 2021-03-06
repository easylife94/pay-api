package com.pay.api.web.controller;

import com.pay.api.core.utils.SpringContextUtil;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenwei
 * @date 2019/1/14 14:59
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @ResponseBody
    @RequestMapping("/feign")
    public String testFeign(){
        return payCenterFeignServiceClient.test();
    }

    @ResponseBody
    @RequestMapping("/bean/{id}")
    public String getBean(@PathVariable("id") String id){

        Object bean = SpringContextUtil.getBean(id);
        return "ok";
    }
}
