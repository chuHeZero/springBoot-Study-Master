package com.chuhezero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuhezero.mapper.UserMapper;
import com.chuhezero.entity.UserEntity;
import com.chuhezero.service.UserService;



@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/test1")
    public boolean test1(@RequestBody UserEntity userEntity) {
        System.out.println("请求参数:" + userEntity);
        try {
            userService.test1(userEntity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("最后查询的数据:" + userMapper.findById(userEntity.getId()));
        return true;
    }

    @PostMapping("/test2")
    public boolean test2(@RequestBody UserEntity userEntity) {
        System.out.println("请求参数:" + userEntity);
        userService.test2(userEntity);
        System.out.println("最后查询的数据:" + userMapper.findById(userEntity.getId()));
        return true;
    }


    @PostMapping("/test3")
    public boolean test3(@RequestBody UserEntity userEntity) {
        System.out.println("请求参数:" + userEntity);
        userService.test3(userEntity);
        System.out.println("最后查询的数据:" + userMapper.findById(userEntity.getId()));
        return true;
    }

    @PostMapping("/test4")
    public boolean test4(@RequestBody UserEntity userEntity) {
        System.out.println("请求参数:" + userEntity);
        userService.test4(userEntity);
        System.out.println("最后查询的数据:" + userMapper.findById(userEntity.getId()));
        return true;
    }


}
