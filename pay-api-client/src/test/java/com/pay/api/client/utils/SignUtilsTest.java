package com.pay.api.client.utils;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.method.ApiPayUnifiedPayDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

/**
 * @author chenwei
 * @date 2019/2/20 17:38
 */
public class SignUtilsTest {

    private String sysPubKey;

    private String sysPriKey;

    private String pubKey;

    private String priKey;

    private ApiPayDTO apiPayDTO;

    @Before
    public void before() throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException {
        apiPayDTO = new ApiPayDTO();
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


        priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN9F4HdZxeWtQetF\n" +
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

        pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfReB3WcXlrUHrRTy4MRElnWj1\n" +
                "6pn4N9702uw04qc8rxQeK5Mg0EcGT8NOlwxvq5Cs1xGjagg4DYj1ytEDu53/xJ6O\n" +
                "fNIz4uV4yDJOJzoxkE8WSfiBtTPgtqPiz++jUUqIQ62HS+BZFPOTtrec/GycNJnK\n" +
                "kNo0AHWXgIU98yBubwIDAQAB";

        sysPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC990u0LNbpqkBvqdfOykW0OFag\n" +
                "YsP60IUY+9L9kXmnF+VG9Du9HJ4apj/DrXUWGjG1qaSAHEAXDM2lH9t8Ra9fqB3T\n" +
                "C+qfF3e9tBM2WVlQrXwwm8w2J/JrV6aDtW7RuLY3whj2o7Wt1TKMCTRJXbhbyRfR\n" +
                "dZJgTLvgzRyaRyc0mwIDAQAB";

        sysPriKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL33S7Qs1umqQG+p\n" +
                "187KRbQ4VqBiw/rQhRj70v2ReacX5Ub0O70cnhqmP8OtdRYaMbWppIAcQBcMzaUf\n" +
                "23xFr1+oHdML6p8Xd720EzZZWVCtfDCbzDYn8mtXpoO1btG4tjfCGPajta3VMowJ\n" +
                "NElduFvJF9F1kmBMu+DNHJpHJzSbAgMBAAECgYARvsoZmJ6y0S6Xv7vjG04Lmb+t\n" +
                "18wK3/Ou88cv3KkKjNbkG+iAr+2s+561PrzTRbsjByfQt9Lc1FtZc3JjV3ccRNsG\n" +
                "Zgim5TNii7+Qp7jT2pgjvTPYFkNRwcODAibJCoWBPd7NZIf4eoxaCailFSANBxSg\n" +
                "PaXTEepmWoMBmuDiEQJBAPxPW/TjTdtijJCrEmsWa36Wmkg8/b9R6A1+IL3GCZ3W\n" +
                "Zf1xDI3sHRh98Vtw0hJ+xtJ0Y9Afr+e9MhMQ4WC0qt0CQQDAvoXD2ojwt8jia2KZ\n" +
                "+yfyQ2jGJ2DMgmd6ccit0fZ3btdl0IHSqCdWjhvOr7KZX8uyEvBvwMWl6oQJMG6j\n" +
                "mrnXAkBFGAprno+ko3Ew8NgjYUlgqidn3uPyL6DniQG4VnItuhfF9t5kQyOYoGyn\n" +
                "CNdOX09dNKhcx0CpqoL6+6VskS01AkEAmcIjQbd/END8LyvNT9sc5dXCqnjVbrAE\n" +
                "zpggqBFcFbDVokIwnpVINHNPWZWt206hV6BmMDBPO+mWhSIe0OLopwJBAMhjFYio\n" +
                "g2T50BQU0JEtQmR9L07+N4QPwT+y67mdak5GgkkxE7a79Jyj9yliLD5wcKJDm5mM\n" +
                "KL2WbaXq3B2dAH8=";

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void signRsa() throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException {
        apiPayDTO.setSign(SignUtils.signRsa(SignUtils.formatStr(apiPayDTO.getContent()),priKey));

        boolean b = SignUtils.verifyRsa(SignUtils.formatStr(apiPayDTO.getContent()), pubKey, apiPayDTO.getSign());
        System.out.println(b);
        Assert.assertTrue(b);
    }

    @Test
    public void verifyRsa() {
    }

    @Test
    public void formatStr() {
    }

    @Test
    public void str() {
    }
}