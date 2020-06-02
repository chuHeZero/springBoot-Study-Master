package com.chuhe.controller;

import com.chuhe.entity.UserEntity;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * 在浏览器输入 http://localhost:8848/swagger-ui.html 即可查看
 *
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param user
     * @return
     * @ApiOperation注解来给API增加说明、通过@ApiImplicitParams注解来给参数增加说明。 value 是标题,notes是详细说明
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserEntity")
    @PostMapping("/user")
    public boolean insert(@RequestBody UserEntity user) {
        logger.info("开始新增用户信息！请求参数:{}", user);
        return true;
    }

    @ApiOperation(value = "更新用户", notes = "根据User对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserEntity")
    @PutMapping("/user")
    public boolean update(@RequestBody UserEntity user) {
        logger.info("开始更新用户信息！请求参数:{}", user);
        return true;
    }

    @ApiOperation(value = "删除用户", notes = "根据User对象删除用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserEntity")
    @DeleteMapping("/user")
    public boolean delete(@RequestBody UserEntity user) {
        logger.info("开始删除用户信息！请求参数:{}", user);
        return true;
    }

    /**
     * 注：@GetMapping("/user")是spring 4.3的新注解等价于：
     *
     * @param user
     * @return
     * @RequestMapping(value = "/user", method = RequestMethod.GET)
     */
    @ApiOperation(value = "获取用户列表", notes = "根据User对象查询用户信息")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserEntity")
    @GetMapping("/user")
    public UserEntity findByUser(UserEntity user) {
        logger.info("开始查询用户列表，请求参数:{}", user);
        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setAge(18);
        user2.setName("chuhe");
        return user2;
    }
}
