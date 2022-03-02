package com.example.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: xiaoHuoLong
 * @Date: 2022/2/28 11:03
 */
@SpringBootApplication
@MapperScan("com.example.manage.mapper")
public class ManageApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApiApplication.class, args);
    }
}
