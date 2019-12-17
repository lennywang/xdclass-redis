package com.xdclass.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 *
 **/
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static double size = Math.pow(2, 32);

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        this.redisTemplate = redisTemplate;
    }

    ;

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            redisTemplate.opsForList();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object get(final String key) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean desc(final String key, int value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key, -value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
