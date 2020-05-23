package com.chuhezero.controller;

import com.chuhezero.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @PostMapping("/hello")
    public String index(@RequestBody UserEntity user) {
        System.out.println("user:" + user);
        return "Hello " + user.getName() + "，age  " + user.getAge();
    }
}
