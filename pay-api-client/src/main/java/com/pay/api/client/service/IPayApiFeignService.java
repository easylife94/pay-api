package com.pay.api.client.service;

import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberCreateResultFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateResultFeignDTO;
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

    /**
     * 生成交易会员
     *
     * @param tradeMemberCreateFeignDTO 创建参数
     * @return 结果参数
     */
    @RequestMapping(value = "/service/tradeMember/create", method = RequestMethod.POST)
    TradeMemberCreateResultFeignDTO tradeMemberCreate(TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO);

    /**
     * 更新交易会员
     *
     * @param tradeMemberUpdateFeignDTO 更新参数
     * @return 结果参数
     */
    @RequestMapping(value = "/service/tradeMember/update", method = RequestMethod.POST)
    TradeMemberUpdateResultFeignDTO tradeMemberUpdate(TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO);
}
