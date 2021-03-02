package com.ourline.ourlineserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OurlineServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlineServeApplication.class, args);
    }

}
