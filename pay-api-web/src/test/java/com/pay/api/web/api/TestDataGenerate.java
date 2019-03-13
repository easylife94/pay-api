package com.pay.api.web.api;

import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.core.dao.TradeMemberDao;
import com.pay.api.core.service.IIdService;
import com.pay.api.web.PayApiWebApplicationTests;
import com.pay.center.client.constants.AgentLevelEnum;
import com.pay.center.client.constants.DefrayalChannelEnum;
import com.pay.center.client.constants.DefrayalTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author chenwei
 * @date 2019/3/13 16:52
 */
public class TestDataGenerate extends PayApiWebApplicationTests {

    private final IIdService idService;
    private final TradeMemberDao tradeMemberDao;

    @Autowired
    public TestDataGenerate(IIdService idService, TradeMemberDao tradeMemberDao) {
        this.idService = idService;
        this.tradeMemberDao = tradeMemberDao;
    }

    /**
     * 生成交易压力测试数据
     *
     * @throws Exception
     */
    @Test
    public void generateTradePressureTestData() throws Exception {

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

        String sysPriKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL33S7Qs1umqQG+p\n" +
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

        Date date = new Date();
        //统一测试代理商id
        long testAgentId = 9999999999L;
        //统一测试代理商名称
        String testAgentName = "TEST-AGENT";
        //统一测试代理商编号
        String testAgentNumber = "TEST-AGENT-9999999999";
        //测试会员数量
        int memberCount = 500;
        //测试会员通道下商户数量
        int memberChannelMerchantCount = 10;
        //测试通道数量
        int channelCount = 10;
        //测试支付渠道和支付方式
        Map<String, List<String>> defrayal = new HashMap<>();
        List<String> aliDefrayalChannel = new ArrayList<>();
        aliDefrayalChannel.add(DefrayalTypeEnum.NATIVE.getType());
        aliDefrayalChannel.add(DefrayalTypeEnum.JSAPI.getType());
        List<String> wechatDefrayalChannel = new ArrayList<>();
        wechatDefrayalChannel.add(DefrayalTypeEnum.NATIVE.getType());
        wechatDefrayalChannel.add(DefrayalTypeEnum.JSAPI.getType());
        defrayal.put(DefrayalChannelEnum.ALI.getName(), aliDefrayalChannel);
        defrayal.put(DefrayalChannelEnum.WECHAT.getName(), wechatDefrayalChannel);


        //2.生成交易通道配置数据
        for (int i = 0; i < channelCount; i++) {

        }


        for (int i = 0; i < memberCount; i++) {
            //1.生成交易会员数据
            TradeMemberDO tradeMemberDO = new TradeMemberDO();
            tradeMemberDO.setId(idService.generateId());
            tradeMemberDO.setAgentId(testAgentId);
            tradeMemberDO.setAgentLevel(AgentLevelEnum.LEVEL_1.getName());
            tradeMemberDO.setAgentName(testAgentName);
            tradeMemberDO.setAgentNumber(testAgentNumber);
            tradeMemberDO.setGmtCreate(date);
            tradeMemberDO.setIsDeleted(false);
            tradeMemberDO.setMemberId(idService.generateId());
            tradeMemberDO.setMemberName("TEST-MEMBER-" + i);
            tradeMemberDO.setMemberNumber("TEST-MEMBER-" + tradeMemberDO.getId());
            tradeMemberDO.setMemberPubKey(pubKey);
            tradeMemberDO.setSysPriKey(sysPubKey);
            tradeMemberDO.setSysPubKey(sysPriKey);
            tradeMemberDao.insert(tradeMemberDO);


        }

        //4.生成交易路由数据


        //3.生成交易商户配置数据

    }
}
