package com.xdclass.redis.controller;

import com.xdclass.redis.service.BloomFilterService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 **/
@RestController
@RequestMapping("/bloom")
public class BloomFilterController {

    @Resource
    private BloomFilterService bloomFilterService;

    @RequestMapping("/idExists")
    public boolean ifExists(Integer id)
    {
        return bloomFilterService.userIdExists(id);
    }

}
