package com.chuhezero.controller;

import com.chuhezero.entity.UserEntity;
import com.chuhezero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean insert(@RequestBody UserEntity user) {
        System.out.println("主数据源开始新增...");
        return userService.insert(user);
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean update(@RequestBody UserEntity user) {
        System.out.println("主数据源开始更新...");
        return userService.update(user);
    }


    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody UserEntity user) {
        System.out.println("主数据源开始删除...");
        return userService.delete(user);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<UserEntity> findByUser(UserEntity user) {
        System.out.println("主数据源开始查询...");
        return userService.findByListEntity(user);
    }
}
