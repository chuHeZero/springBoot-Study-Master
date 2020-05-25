package com.chuhezero.controller;


import com.chuhezero.entity.UserEntity;
import com.chuhezero.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public boolean addUser(@RequestBody UserEntity user) {
        logger.info("开始新增...");
        return userService.addUser(user);
    }

    @PutMapping("/user")
    public boolean updateUser(@RequestBody UserEntity user) {
        logger.info("开始更新...");
        return userService.updateUser(user);
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestParam(value = "id", required = true) int userId) {
        logger.info("开始删除...");
        return userService.deleteUser(userId);
    }


    @GetMapping("/user")
    public UserEntity findByUserId(@RequestParam(value = "id", required = true) int userId) {
        logger.info("开始查询所有数据...");
        return userService.findByUserId(userId);
    }
}
