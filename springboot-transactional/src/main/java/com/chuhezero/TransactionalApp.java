package com.chuhezero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
@SpringBootApplication
public class TransactionalApp {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApp.class, args);
        System.out.println("Transactional 程序正在运行...");

    }
}
