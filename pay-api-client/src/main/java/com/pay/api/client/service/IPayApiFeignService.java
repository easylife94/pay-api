package com.pay.api.client.service;

import com.pay.api.client.dto.rest.*;
import org.springframework.web.bind.annotation.RequestBody;
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
    TradeMemberCreateResultFeignDTO tradeMemberCreate(@RequestBody TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO);

    /**
     * 更新交易会员
     *
     * @param tradeMemberUpdateFeignDTO 更新参数
     * @return 结果参数
     */
    @RequestMapping(value = "/service/tradeMember/update", method = RequestMethod.POST)
    TradeMemberUpdateResultFeignDTO tradeMemberUpdate(@RequestBody TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO);

    /**
     * 创建交易通道配置
     *
     * @param tradeChannelConfigCreateFeignDTO 创建参数
     * @return 结果参数
     */
    @RequestMapping(value = "/service/tradeChannel/create", method = RequestMethod.POST)
    TradeChannelConfigCreateResultFeignDTO tradeChannelCreate(@RequestBody TradeChannelConfigCreateFeignDTO tradeChannelConfigCreateFeignDTO);

    /**
     * 更新交易通道配置
     *
     * @param tradeChannelConfigUpdateFeignDTO 更新参数
     * @return 结果参数
     */
    @RequestMapping(value = "/service/tradeChannel/update", method = RequestMethod.POST)
    TradeChannelConfigUpdateResultFeignDTO tradeChannelUpdate(@RequestBody TradeChannelConfigUpdateFeignDTO tradeChannelConfigUpdateFeignDTO);
}
