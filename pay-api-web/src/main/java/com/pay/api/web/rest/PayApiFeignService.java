package com.pay.api.web.rest;

import com.pay.api.client.service.IPayApiFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTfull服务实现
 *
 * @author chenwei
 * @date 2019/1/14 14:15
 */
@RestController
public class PayApiFeignService implements IPayApiFeignService {

    private static Logger logger = LoggerFactory.getLogger(PayApiFeignService.class);

    @Override
    public String test() {
        logger.info("test service is available");
        return "service is available";
    }
}
