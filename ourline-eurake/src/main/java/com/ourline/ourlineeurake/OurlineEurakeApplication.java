package com.ourline.ourlineeurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class OurlineEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlineEurakeApplication.class, args);
    }

}
