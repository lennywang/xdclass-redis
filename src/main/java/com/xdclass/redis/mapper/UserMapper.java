package com.xdclass.redis.mapper;

import com.xdclass.redis.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert sys_user(id,user_name) values(#{id},#{userName})")
    void insert(User user);

    @Results(
            id="userResult",
            value = {
                    @Result(property = "userName",column = "user_name")
            }
    )
    @Select("select id,user_name,image from sys_user where id=#{id}")
    User find(@Param("id")String id);
}
