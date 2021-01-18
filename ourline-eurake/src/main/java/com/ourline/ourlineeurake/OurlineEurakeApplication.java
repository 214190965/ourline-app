package com.ourline.ourlineeurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OurlineEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlineEurakeApplication.class, args);
    }

}
