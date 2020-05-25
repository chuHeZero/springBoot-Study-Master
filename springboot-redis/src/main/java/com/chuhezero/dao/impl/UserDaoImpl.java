package com.chuhezero.dao.impl;

import com.alibaba.fastjson.JSON;
import com.chuhezero.dao.UserDao;
import com.chuhezero.entity.UserEntity;
import com.chuhezero.util.RedisUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private RedisUtil redisUtil;


    /**
     *
     */
    @Override
    public void addUser(UserEntity user) {
        redisUtil.set(String.valueOf(user.getId()), user.toString());
    }


    /**
     *
     */
    @Override
    public void updateUser(UserEntity user) {
        redisUtil.set(String.valueOf(user.getId()), user.toString());
    }


    /**
     *
     */
    @Override
    public void deleteUser(int id) {
        redisUtil.del(String.valueOf(id));
    }


    /**
     *
     */
    @Override
    public UserEntity findByUserId(int id) {
        String data = redisUtil.get(String.valueOf(id)).toString();
        return JSON.parseObject(data, UserEntity.class);
    }
}
