package com.pay.api.client.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenwei
 * @date 2019/1/15 13:45
 */
public interface IPayApiFeignService {

    /**
     * 测试服务是否可用
     *
     * @return
     */
    @RequestMapping(value = "/service/test", method = RequestMethod.POST)
    String test();
}
