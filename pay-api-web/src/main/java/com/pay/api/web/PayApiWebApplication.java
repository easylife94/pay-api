package com.pay.api.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chenwei
 */
@SpringBootApplication(scanBasePackages = {"com.pay.*"})
@MapperScan("com.pay.api.core.dao")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.pay.*"})
public class PayApiWebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PayApiWebApplication .class);
        springApplication.run(args);
    }

}

