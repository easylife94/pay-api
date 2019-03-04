package com.pay.api.web.api;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.method.ApiPayOrderQueryDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import com.pay.api.client.model.TradeRouteDO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.client.utils.SignUtils;
import com.pay.api.core.dao.TradeRouteDao;
import com.pay.api.web.PayApiWebApplicationTests;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * @author chenwei
 * @date 2019/1/16 17:59
 */
public class PayApiTest extends PayApiWebApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    private TradeRouteDao tradeRouteDao;

    @Value("${trade.warn-times-max}")
    private Integer warnTimesMax;

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
        apiPayDTO.setMember("A00001");
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        //设置返回结果加密
        apiPayDTO.setEncrypt(true);

        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = new ApiPayUnifiedPayDTO();
        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试下单");
        apiPayUnifiedPayDTO.setDefrayalChannel("ALI");
        apiPayUnifiedPayDTO.setDefrayalType("NATIVE");
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //尝试解密
    }

    @Test
    public void testTradeWarn() throws Exception {
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

        //测试会员
        String memberNumber = "A00001";

        TradeRouteDO tradeRouteDO = tradeRouteDao.selectByPrimaryKey(2L);
        Assert.assertTrue("测试交易预警路由不存在", tradeRouteDO != null);
        Assert.assertTrue("测试交易预警路由不属于待测试会员", StringUtils.equals(tradeRouteDO.getMemberNumber(), memberNumber));


        //设置预警次数为    warnTimeMax - 1
        tradeRouteDO.setTradeWarnDate(new Date());
        tradeRouteDO.setTradeWarnTimes(warnTimesMax - 1);
        tradeRouteDO.setTradeRisk(false);
        tradeRouteDao.updateByPrimaryKey(tradeRouteDO);


        ApiPayDTO apiPayDTO = new ApiPayDTO();
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember(memberNumber);
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = new ApiPayUnifiedPayDTO();
        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试触发预警下单");
        apiPayUnifiedPayDTO.setMerchantNumber(tradeRouteDO.getMerchantNumber());
        apiPayUnifiedPayDTO.setDefrayalChannel(tradeRouteDO.getDefrayalChannel());
        apiPayUnifiedPayDTO.setDefrayalType(tradeRouteDO.getDefrayalType());
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        //触发预警后再下单
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember(memberNumber);
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试达到预警次数下单");
        apiPayUnifiedPayDTO.setMerchantNumber(tradeRouteDO.getMerchantNumber());
        apiPayUnifiedPayDTO.setDefrayalChannel(tradeRouteDO.getDefrayalChannel());
        apiPayUnifiedPayDTO.setDefrayalType(tradeRouteDO.getDefrayalType());
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testTradeRisk() throws Exception {
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

        //测试会员
        String memberNumber = "A00001";

        TradeRouteDO tradeRouteDO = tradeRouteDao.selectByPrimaryKey(3L);
        Assert.assertTrue("测试交易预警路由不存在", tradeRouteDO != null);
        Assert.assertTrue("测试交易预警路由不属于待测试会员", StringUtils.equals(tradeRouteDO.getMemberNumber(), memberNumber));


        //设置风控为false
        tradeRouteDO.setTradeRisk(false);
        tradeRouteDao.updateByPrimaryKey(tradeRouteDO);


        ApiPayDTO apiPayDTO = new ApiPayDTO();
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember(memberNumber);
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = new ApiPayUnifiedPayDTO();
        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试触发风控下单");
        apiPayUnifiedPayDTO.setMerchantNumber(tradeRouteDO.getMerchantNumber());
        apiPayUnifiedPayDTO.setDefrayalChannel(tradeRouteDO.getDefrayalChannel());
        apiPayUnifiedPayDTO.setDefrayalType(tradeRouteDO.getDefrayalType());
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        //触发预警后再下单
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember(memberNumber);
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试触发风控下单");
        apiPayUnifiedPayDTO.setMerchantNumber(tradeRouteDO.getMerchantNumber());
        apiPayUnifiedPayDTO.setDefrayalChannel(tradeRouteDO.getDefrayalChannel());
        apiPayUnifiedPayDTO.setDefrayalType(tradeRouteDO.getDefrayalType());
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testTradeOrderQuery() throws Exception {
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
        apiPayDTO.setMember("A00001");
        apiPayDTO.setMethod("UNIFIED_PAY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        ApiPayUnifiedPayDTO apiPayUnifiedPayDTO = new ApiPayUnifiedPayDTO();
        apiPayUnifiedPayDTO.setTradeAmount("0.01");
        apiPayUnifiedPayDTO.setSubject("测试下单");
        apiPayUnifiedPayDTO.setDefrayalChannel("ALI");
        apiPayUnifiedPayDTO.setDefrayalType("NATIVE");
        apiPayUnifiedPayDTO.setCurrency("CNY");
        apiPayUnifiedPayDTO.setNotifyUrl("http://baidu.com");
        apiPayUnifiedPayDTO.setMemberOrderNumber(System.currentTimeMillis() + "");

        apiPayDTO.setContent(JSONObject.toJSONString(apiPayUnifiedPayDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayUnifiedPayDTO), priKey));


        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        //查询订单
        apiPayDTO = new ApiPayDTO();
        apiPayDTO.setEncrypt(false);
        apiPayDTO.setFormat("JSON");
        apiPayDTO.setMember("A00001");
        apiPayDTO.setMethod("ODER_QUERY");
        apiPayDTO.setSignType("RSA");
        apiPayDTO.setTimestamp(DateUtils.nowTime2Str());
        apiPayDTO.setVersion("1.0");

        ApiPayOrderQueryDTO apiPayOrderQueryDTO = new ApiPayOrderQueryDTO(null, apiPayUnifiedPayDTO.getMemberOrderNumber());
        apiPayDTO.setContent(JSONObject.toJSONString(apiPayOrderQueryDTO));
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.str(apiPayOrderQueryDTO), priKey));

        mvc.perform(MockMvcRequestBuilders.post("/api/pay/gateway")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(apiPayDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}