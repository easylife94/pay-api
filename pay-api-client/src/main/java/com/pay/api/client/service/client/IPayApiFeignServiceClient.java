package com.pay.api.client.service.client;

import com.pay.api.client.service.IPayApiFeignService;
import com.pay.api.client.service.hystric.PayApiFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenwei
 * @date 2019/1/15 13:46
 */
@FeignClient(value = "pay-api", fallback = PayApiFeignServiceFallback.class)
public interface IPayApiFeignServiceClient extends IPayApiFeignService {

}
