package com.ourline.ourlinezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication(scanBasePackages = "com.ourline")
@EnableZuulProxy
public class OurlineZuulApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OurlineZuulApplication.class, args);
    }

    /**
     * 把springboot打成war包,用外置tomcat启动？
     * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OurlineZuulApplication.class);
    }
}
