package com.cohen.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit timeUnit){
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate.execute(new SessionCallback<Boolean>() {
            @Override
            public Boolean execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch(key);
                redisOperations.multi();
                redisOperations.opsForValue().setIfAbsent(key, value);
                redisOperations.expire(key, timeout, timeUnit);
                List exec = redisOperations.exec();
                return (exec != null && exec.size() == 2 && (Boolean)exec.get(0));
            }
        });
    }
}
