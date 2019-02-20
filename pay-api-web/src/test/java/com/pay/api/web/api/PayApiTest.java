package com.pay.api.web.api;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.client.utils.RsaUtils;
import com.pay.api.client.utils.SignUtils;
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
        String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN9F4HdZxeWtQetF\n" +
                "PLgxESWdaPXqmfg33vTa7DTipzyvFB4rkyDQRwZPw06XDG+rkKzXEaNqCDgNiPXK\n" +
                "0QO7nf/Eno580jPi5XjIMk4nOjGQTxZJ+IG1M+C2o+LP76NRSohDrYdL4FkU85O2\n" +
                "t5z8bJw0mcqQ2jQAdZeAhT3zIG5vAgMBAAECgYEAy9SJJaxpRFK12UluM2FoHATm\n" +
                "a4rvYXHwM20hMu6wanATV6/EM7KxBIwQ61BuZAwmmgQF8D++nR2OKYYs5tGDXOW3\n" +
                "wFyHXXAdSMM+6BnPddzzb+vecK+P3pRbMjjXrXwqwrEYELjgro/uBdMDONsen2o+\n" +
                "OMkUqKggWBx3fHSN2KECQQD2J63PrknRaFVuOKQql/OHAy1xte917ZgsopZs99gF\n" +
                "eL8GLZPI2aDwjM5Yg04Uz4kRqEE3UgUVzwTtMTfzpR6XAkEA6DPqC7G0e6R7ak31\n" +
                "fvEgo6cPqAcphP2jMEVwnoVEQhsG2Myt5FHmaRChpOtw5Nw6NxtRm9ATnVQi6ain\n" +
                "aS8B6QJADkD/9J3AEos7HzXSc9D2viO19va1FhwbCsKjeU3kyXRTg3USMLhBdIyC\n" +
                "ymdYFyZpZodat2xddQTW4TFPbyFpowJBAMO9r5h4DxsDhv4QBdkiz07lr68HipqP\n" +
                "bZdtkggvc2D+g4ES2avU1pTO7lSmHJ7wfyqhHuRoPYCswlmSUL28YLECQAzBroIB\n" +
                "TgjN2+r1aeYDlXZY2FVERoMRB2yWsI3DttT3tOmttmAJ2PGuU2ietaTEpHCqS6pr\n" +
                "2GBVxIYCp0xMNHk=";

        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfReB3WcXlrUHrRTy4MRElnWj1\n" +
                "6pn4N9702uw04qc8rxQeK5Mg0EcGT8NOlwxvq5Cs1xGjagg4DYj1ytEDu53/xJ6O\n" +
                "fNIz4uV4yDJOJzoxkE8WSfiBtTPgtqPiz++jUUqIQ62HS+BZFPOTtrec/GycNJnK\n" +
                "kNo0AHWXgIU98yBubwIDAQAB";

        String sysPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC990u0LNbpqkBvqdfOykW0OFag\n" +
                "YsP60IUY+9L9kXmnF+VG9Du9HJ4apj/DrXUWGjG1qaSAHEAXDM2lH9t8Ra9fqB3T\n" +
                "C+qfF3e9tBM2WVlQrXwwm8w2J/JrV6aDtW7RuLY3whj2o7Wt1TKMCTRJXbhbyRfR\n" +
                "dZJgTLvgzRyaRyc0mwIDAQAB";

        ApiPayDTO apiPayDTO = new ApiPayDTO();
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember("1");
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = new ApiPayUnifiedPayDTO();
        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试下单");


        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO),priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
    }
}