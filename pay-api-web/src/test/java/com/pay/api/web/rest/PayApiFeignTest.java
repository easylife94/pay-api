package com.pay.api.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.client.service.client.IPayApiFeignServiceClient;
import com.pay.api.core.service.IIdService;
import com.pay.api.web.PayApiWebApplicationTests;
import com.pay.center.client.constants.AgentLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    @Autowired
    IPayApiFeignServiceClient payApiFeignServiceClient;

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
    public void testTradeMemberCreate() throws Exception {
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfReB3WcXlrUHrRTy4MRElnWj1\n" +
                "6pn4N9702uw04qc8rxQeK5Mg0EcGT8NOlwxvq5Cs1xGjagg4DYj1ytEDu53/xJ6O\n" +
                "fNIz4uV4yDJOJzoxkE8WSfiBtTPgtqPiz++jUUqIQ62HS+BZFPOTtrec/GycNJnK\n" +
                "kNo0AHWXgIU98yBubwIDAQAB";
        TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO = new TradeMemberCreateFeignDTO(idService.generateId(),"TEST-A-" + idService.generateId(),"测试创建交易会员",pubKey,idService.generateId(),"TEST-A-" + idService.generateId(),"不存在代理商", AgentLevelEnum.LEVEL_1.getType());

        mvc.perform(MockMvcRequestBuilders.post("/service/tradeMember/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(tradeMemberCreateFeignDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
