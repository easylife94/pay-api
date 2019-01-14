package com.pay.api.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pay.*"})
//@MapperScan("com.pay.api.core.dao")
public class PayApiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApiWebApplication.class, args);
    }

}

