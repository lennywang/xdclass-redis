package com.xdclass.redis.controller;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;
import com.xdclass.redis.domain.RedPacketInfo;
import com.xdclass.redis.domain.RedPacketRecord;
import com.xdclass.redis.mapper.RedPacketInfoMapper;
import com.xdclass.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/redpacket")
public class RedPacketController {

    @Autowired
    private RedisService redisService;

    @Autowired(required = false)
    private RedPacketInfoMapper redPackageInfoMapper;

    private static final String TOTAL_NUM="_totalNum";
    private static final String TOTAL_AMONUNT="_totalAmount";

    @ResponseBody
    @RequestMapping("/addPacket")
    public String savePacket(@Param("uid") Integer uId,@Param("totalNum") Integer totalNum,@Param("totalAmount") Integer totalAmount) {
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setUid(uId);
        redPacketInfo.setTotalAmount(totalAmount);
        redPacketInfo.setTotalPacket(totalNum);
        redPacketInfo.setCreateTime(new Date());
        redPacketInfo.setRemainingAmount(totalAmount);
        redPacketInfo.setRemainingPacket(totalNum);
        long redPacketId = System.currentTimeMillis();
        redPacketInfo.setRedPacketId(redPacketId);
        redPackageInfoMapper.insert(redPacketInfo);
        redisService.set(redPacketId + TOTAL_NUM, totalNum + "");
        redisService.set(redPacketId + TOTAL_AMONUNT, totalAmount+"");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/getPacket")
    public Integer getPacket(long packetId)
    {
       String redPacketId= packetId+TOTAL_NUM;
        String num = (String)redisService.get(redPacketId);
        if (StringUtils.isNotBlank(num))
        {
            return Integer.parseInt(num);
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/getRedPacketMoney")
    public String getRedPacketMoney(int uid,long redPacketId)
    {
        Integer randomAmount=0;
        String redPacketName = redPacketId + TOTAL_NUM;
        String totalAmountName = redPacketId + TOTAL_AMONUNT;
        String num = (String) redisService.get(redPacketName);
        if (StringUtils.isBlank(num)||Integer.parseInt(num)==0)
        {
            return "抱歉，红包已经抢完了！";
        }
        String totalAmount = (String) redisService.get(totalAmountName);
        if (StringUtils.isNotBlank(totalAmount)){
            int totalAmountInt = Integer.parseInt(totalAmount);
            int totalNumInt = Integer.parseInt(num);
            int maxMoney = totalAmountInt / totalNumInt * 2;
            Random random = new Random();
            randomAmount = random.nextInt(maxMoney);
        }

        redisService.desc(redPacketName,1);
        redisService.desc(totalAmountName,randomAmount);
        updateRedPacketDB(uid,redPacketId,randomAmount);
        return randomAmount+"";
    }

    public void updateRedPacketDB(int uid,long redPacketId,int amount)
    {
        RedPacketRecord redPacketRecord = new RedPacketRecord();
        redPacketRecord.setUid(uid);
        redPacketRecord.setRedPacketId(redPacketId);
        redPacketRecord.setAmount(1111);
        redPacketRecord.setCreateTime(new Date());
        redPackageInfoMapper.insertRedPacketRecord(redPacketRecord);
    }


}
