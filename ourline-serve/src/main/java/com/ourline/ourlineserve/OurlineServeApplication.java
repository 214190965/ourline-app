package com.ourline.ourlineserve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.ourline")
@EnableEurekaClient
@MapperScan(basePackages = {"com.ourline.ourlineserve.mapper"})
public class OurlineServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlineServeApplication.class, args);
    }

}
