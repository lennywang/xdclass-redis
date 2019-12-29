package com.xdclass.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public boolean incr(final String key) {
        boolean result = false;
        try {

            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key, 1);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean bloomFilterExists(String filterName,int value)
    {
        DefaultRedisScript<Boolean> bloomExists = new DefaultRedisScript<Boolean>();
        bloomExists.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterExist.lua")));
        bloomExists.setResultType(Boolean.class);
        List<Object> objects = new ArrayList<>();
        objects.add(filterName);
        objects.add(value);// ?
        Boolean result = (Boolean) redisTemplate.execute(bloomExists, objects);
        return result;
    }

    public boolean bloomFilterAdd(String filterName,int value)
    {
        DefaultRedisScript<Boolean> bloomAdd = new DefaultRedisScript<Boolean>();
        bloomAdd.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterAdd.lua")));
        bloomAdd.setResultType(Boolean.class);
        List<Object> objects = new ArrayList<>();
        objects.add(filterName);
        objects.add(value);// ?
        Boolean result = (Boolean) redisTemplate.execute(bloomAdd, objects);
        return result;
    }

    public boolean getAndIncrLua(String key){
        DefaultRedisScript<Boolean> bloomExists = new DefaultRedisScript<>();
        bloomExists.setScriptSource(new ResourceScriptSource(new ClassPathResource("secKillIncr.lua")));
        bloomExists.setResultType(Boolean.class);
        List<Object> objects = new ArrayList<>();
        objects.add(key);// ?
        Boolean result = (Boolean) redisTemplate.execute(bloomExists, objects);
        return  result;
    }

}
