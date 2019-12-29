package com.xdclass.redis.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.xdclass.redis.domain.User;
import com.xdclass.redis.mapper.UserMapper;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 **/
public class BloomFilterService {

    @Resource
    private UserMapper userMapper;

    private BloomFilter<Integer> bf;

    @PostConstruct
    public void initBloomFilter()
    {
        User user = new User();
        List<User> userList = userMapper.findList();
        if (CollectionUtils.isEmpty(userList)){
            return;
        }
        bf=BloomFilter.create(Funnels.integerFunnel(),userList.size());
        for (User u:userList){
            bf.put(u.getId());
        }
    }

    public boolean userIdExists(int id)
    {
        return bf.mightContain(id);
    }
}
