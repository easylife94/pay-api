package com.pay.api.client.service.hystric;

import com.pay.api.client.service.client.IPayApiFeignServiceClient;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019/1/15 13:50
 */
@Component
public class PayApiFeignServiceFallback implements IPayApiFeignServiceClient {
    @Override
    public String test() {
        return "Sorry pay api service is unavailable";
    }
}
