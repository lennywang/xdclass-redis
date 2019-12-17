package com.xdclass.redis.controller;

import com.xdclass.redis.domain.User;
import com.xdclass.redis.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(String id)
    {
        User user = userMapper.find(id);
        //System.out.println(user);
        return user;
    }

}
