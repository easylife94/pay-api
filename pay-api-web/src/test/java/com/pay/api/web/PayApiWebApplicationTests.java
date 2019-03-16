package com.pay.api.web;

import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.core.dao.TradeMemberDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApiWebApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private TradeMemberDao tradeMemberDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void redisTest() {
        String key = "test-hello-worldtest-hello-worldtest-hello-worldtest-hello-worldtest-hello-worldtest-hello-worldtest-hello-world";
        String value = "hello redis";
        redisTemplate.boundValueOps(key).set(value);
        long ms = System.currentTimeMillis();
        String redisValue = redisTemplate.boundValueOps(key).get();
        Assert.assertTrue(StringUtils.equals(value, redisValue));
        System.out.println(System.currentTimeMillis() - ms);

    }

    @Test
    public void cacheTest() {
        TradeMemberDO tradeMemberDO = tradeMemberDao.selectByMemberNumber("A00001");
        Assert.assertNotNull(tradeMemberDO);
    }

    public void cachePresureTest(){

    }

    @Test
    public void redisPresureTest(){
        long ms = System.currentTimeMillis();
        String s = redisTemplate.boundValueOps("api:trade:route:member::TEST-MEMBER-266004939254534155:ALI:NATIVE").get();
        System.out.println(s);
        System.out.println(System.currentTimeMillis() - ms);
    }
}

