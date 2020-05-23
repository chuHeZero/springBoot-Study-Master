package com.chuhezero.controller;

import com.chuhezero.entity.UserEntity;

import com.chuhezero.result.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserRestController {


    @GetMapping("/user")
    public ResultBody findByUser() {
        ResultBody resultBody = new ResultBody();
        List<UserEntity> userList = new ArrayList<>();
        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setName("法外狂徒张三");
        user2.setAge(18);
        userList.add(user2);
        resultBody.setCode("0");
        resultBody.setResult(userList.toString());
        return resultBody;
    }


}
