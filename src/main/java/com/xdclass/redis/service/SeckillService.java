package com.xdclass.redis.service;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 *
 **/
public class SeckillService {

    private static final String secStartPrefix = "skuId_start_";
    private static final String secAccess = "skuId_access_";
    private static final String secCount = "skuId_count_";
    private static final String filterName = "skuId_bloomfilter_";
    private static final String bookedName = "skuId_booked_";
    @Resource
    private RedisService redisService;

    public String seckill(int uid, int skuId) {
        String isStart = (String) redisService.get(secStartPrefix + skuId);
        if (StringUtils.isBlank(isStart)) {
            return "还未开始";
        }
        if (isStart.contains("_")) {
            int isStartInt = Integer.parseInt(isStart.split("_")[0]);
            int startTime = Integer.parseInt(isStart.split("_")[1]);
            if (isStartInt == 0) {
                if (startTime > getNow()) {
                    return "还未开始";
                } else {
                    redisService.set(secStartPrefix + skuId, 1);
                }
            } else {
                return "系统异常";
            }

        } else {
            if (Integer.parseInt(isStart) != 1) {
                return "系统异常";
            }
        }
        //流量拦截
        String secAccessName = secAccess + skuId;
        Integer accessNumInt = 0;
        String accessNum = (String) redisService.get(secAccessName);
        if (StringUtils.isNotBlank(accessNum)) {
            accessNumInt = Integer.parseInt(accessNum);
        }
        String skuIdCountName = secCount + skuId;
        int countNumInt = Integer.parseInt((String) redisService.get(skuIdCountName));
        if (accessNumInt > countNumInt * 1.2) {
            return "抢购已经完成，欢迎下次参与";
        } else {
            redisService.incr(secAccessName);
        }
        //信息校验层
        if (redisService.bloomFilterExists(filterName, uid)) {
            return "您已抢购过该商品，请勿重复下发！";
        } else {
            redisService.bloomFilterAdd(filterName, uid);
        }
        boolean isSuccess = redisService.getAndIncrLua(bookedName + skuId);
        if (isSuccess){
            return "恭喜您抢购成功！！！";
        }else{
            return "抢购结束，欢迎下次参与！";
        }
    }

    private long getNow() {
        return System.currentTimeMillis() / 1000;
    }

}
