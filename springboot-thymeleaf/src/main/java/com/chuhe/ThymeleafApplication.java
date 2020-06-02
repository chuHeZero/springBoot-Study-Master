package com.chuhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApplication.class, args);
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        System.out.println("程序正在运行...");
    }

}

