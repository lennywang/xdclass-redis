package com.xdclass.redis.controller;

import com.xdclass.redis.RedisService;
import com.xdclass.redis.domain.RedPacketInfo;
import com.xdclass.redis.mapper.RedPacketInfoMapper;
import com.xdclass.redis.mapper.RedPacketRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 **/
public class RedPacketController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedPacketInfoMapper redPackageInfoMapper;

    @Autowired
    private RedPacketRecordMapper redPackageRecordMapper;

    private static final String TOTAL_NUM="_totalNum";
    private static final String TOTAL_AMONUNT="_totalAmount";

    @ResponseBody
    @RequestMapping("/addPacket")
    public String savePacket(Integer uId,Integer totalNum,Integer totalAmount) {
        RedPacketInfo redPacketInfo = new RedPacketInfo();

        return null;
    }
}
