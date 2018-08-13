package com.cohen.nettyclient;

import com.cohen.config.ConfigParameter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class NettyClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NettyClientApplication.class, args);
        ConfigParameter configParameter = context.getBean(ConfigParameter.class);
        System.out.println(configParameter.getNettyHost());
    }
}
