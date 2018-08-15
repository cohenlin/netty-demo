package com.cohen;

import com.cohen.config.ConfigParameter;
import com.cohen.redis.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableConfigurationProperties
public class NettyClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NettyClientApplication.class, args);
        RedisUtil redisUtil = context.getBean(RedisUtil.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        String key = "key1";
        String value = UUID.randomUUID().toString();
        long timeout = 3600;

        for (int i = 0; i < 500; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Boolean b = redisUtil.setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + " :: " + b);
                }
            });
        }
    }
}
