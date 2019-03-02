package com.pay.api.client.service.hystric;

import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberCreateResultFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateResultFeignDTO;
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

    @Override
    public TradeMemberCreateResultFeignDTO tradeMemberCreate(TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO) {
        TradeMemberCreateResultFeignDTO resultFeignDTO = new TradeMemberCreateResultFeignDTO();
        resultFeignDTO.feignFail("SERVICE_UNAVAILABLE", "服务不可用");
        return resultFeignDTO;
    }

    @Override
    public TradeMemberUpdateResultFeignDTO tradeMemberUpdate(TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO) {
        TradeMemberUpdateResultFeignDTO resultFeignDTO = new TradeMemberUpdateResultFeignDTO();
        resultFeignDTO.feignFail("SERVICE_UNAVAILABLE", "服务不可用");
        return resultFeignDTO;
    }
}
