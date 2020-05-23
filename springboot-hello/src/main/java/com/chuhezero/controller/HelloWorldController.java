package com.chuhezero.controller;


import com.chuhezero.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Title: HelloWorldController
 * Description: springboot 接口测试
 * 首先启动 Application 程序，然后在浏览器输入http://localhost:8080//hello
 * 即可查看信息
 * 在类中添加  @RestController, 默认类中的方法都会以json的格式返回
 * Version:1.0.0
 *
 * @author chuhe
 * @date 2020年5月23日
 */
@RestController
public class HelloWorldController {
    private static Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    public String index() {
        LOG.info("---------开始----------");
        return "Hello World";
    }


    @RequestMapping("/getUser")
    public UserEntity getUser() {
        // 直接获取界面会直接返回 {"id":2,"name":"李四"}
        LOG.info("---------开始----------");
        UserEntity user = new UserEntity();
        user.setId(2);
        user.setName("李四");
        return user;
    }

}
