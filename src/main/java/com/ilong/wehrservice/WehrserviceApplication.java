package com.ilong.wehrservice;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ilong.wehrservice.mapper")
public class WehrserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WehrserviceApplication.class, args);
    }

}
