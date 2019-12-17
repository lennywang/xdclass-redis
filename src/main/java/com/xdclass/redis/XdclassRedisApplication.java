package com.xdclass.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@MapperScan("com.xdclass.redis.mapper")
@SpringBootApplication
@EnableAutoConfiguration
public class XdclassRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdclassRedisApplication.class, args);
	}

}
