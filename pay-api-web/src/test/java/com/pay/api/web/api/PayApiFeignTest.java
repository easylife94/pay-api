package com.pay.api.web.api;

import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.core.service.IIdService;
import com.pay.api.web.PayApiWebApplicationTests;
import com.pay.center.client.constants.AgentLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试服务接口
 *
 * @author chenwei
 * @date 2019-03-04
 */
public class PayApiFeignTest extends PayApiWebApplicationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private IIdService idService;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        //建议使用这种
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 测试交易会员创建
     */
    @Test
    public void testTradeMemberCreate() {
        TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO = new TradeMemberCreateFeignDTO(idService.generateId(),"TEST-A-" + idService.generateId(),"测试创建交易会员","",idService.generateId(),"TEST-A-" + idService.generateId(),"不存在代理商", AgentLevelEnum.LEVEL_1.getType());

    }
}
