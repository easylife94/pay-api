package com.pay.api.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApiWebApplicationTests {

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void pay() throws IOException {
//        //测试支付网关请求
//        String content = "你好";
//        ApiPayDTO dto = new ApiPayDTO();
//        dto.setContent(content);
//        dto.setMethod("ODER_QUERY");
//
//        String json = JSONObject.toJSONString(dto);
//        System.out.println(json);
//        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
//        Request request = new Request.Builder()
//                .url("http://localhost:8081/api/pay/gateway")
//                .post(RequestBody.create(mediaType, json))
//                .build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Response execute = okHttpClient.newCall(request).execute();
//
//        System.out.println(execute.body().string());
//
//
//    }
}

