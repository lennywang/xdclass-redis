package com.xdclass.redis.mapper;

import com.xdclass.redis.domain.RedPacketInfo;
import com.xdclass.redis.domain.RedPacketRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 *
 **/
@Mapper
public interface RedPacketInfoMapper {
    @Insert({
            "insert into red_packet_info (red_packet_id, total_amount, ",
            "total_packet, remaining_amount,",
            "remaining_packet, uid,",
            "create_time, update_time)",
            "values (#{redPacketId,jdbcType=BIGINT}, #{totalAmount,jdbcType=INTEGER}, ",
            "#{totalPacket,jdbcType=INTEGER}, #{remainingAmount,jdbcType=INTEGER},",
            "#{remainingPacket,jdbcType=INTEGER}, #{uid,jdbcType=INTEGER},",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(RedPacketInfo redPacketInfo);


    @InsertProvider(type=RedPacketRecord.class,method="insertRedPacketRecord")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertRedPacketRecord(RedPacketRecord redPacketRecord);

}
