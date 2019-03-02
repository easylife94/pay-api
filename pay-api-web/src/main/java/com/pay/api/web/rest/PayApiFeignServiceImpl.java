package com.pay.api.web.rest;

import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberCreateResultFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateResultFeignDTO;
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
public class PayApiFeignServiceImpl implements IPayApiFeignService {

    private static Logger logger = LoggerFactory.getLogger(PayApiFeignServiceImpl.class);

    @Override
    public String test() {
        logger.info("test service is available");
        return "service is available";
    }

    @Override
    public TradeMemberCreateResultFeignDTO tradeMemberCreate(TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO) {
        TradeMemberCreateResultFeignDTO resultFeignDTO = new TradeMemberCreateResultFeignDTO();
        //todo 生成交易会员
        //生成rsa公钥私钥
        //创建do，保存系统公钥私钥和会员公钥


        return resultFeignDTO;
    }

    @Override
    public TradeMemberUpdateResultFeignDTO tradeMemberUpdate(TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO) {
        TradeMemberUpdateResultFeignDTO resultFeignDTO = new TradeMemberUpdateResultFeignDTO();
        //todo 更新交易会员
        //判断是否需要调用dao更新
        return resultFeignDTO;
    }
}
