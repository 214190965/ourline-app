package com.ourline.ourlineuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = {"com.ourline.ourlineuser.mapper"})
public class OurlineUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlineUserApplication.class, args);
    }

}
