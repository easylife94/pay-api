package com.pay.api.core.platform;

import com.pay.api.core.PlatformComponent;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.center.client.constants.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenwei
 * @date 2019/2/11 16:32
 */
@PlatformComponent(PlatformEnum.TEST)
public class Test implements IPlatformTrade{

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public Test(ITradeOrderService tradeOrderService) {
        this.tradeOrderService = tradeOrderService;
    }




}
