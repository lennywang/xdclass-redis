package com.xdclass.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xdclass.redis.mapper")
@SpringBootApplication
public class XdclassRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdclassRedisApplication.class, args);
	}

}
