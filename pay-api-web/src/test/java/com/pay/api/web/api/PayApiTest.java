package com.pay.api.web.api;

import com.pay.api.web.PayApiWebApplicationTests;
import org.hamcrest.Matchers;
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

import static org.junit.Assert.*;

/**
 * @author chenwei
 * @date 2019/1/16 17:59
 */
public class PayApiTest extends PayApiWebApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        //建议使用这种
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void gateway() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("lat", "123.123").param("lon", "456.456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
    }
}