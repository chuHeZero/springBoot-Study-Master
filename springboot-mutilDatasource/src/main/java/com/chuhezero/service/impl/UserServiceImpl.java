package com.chuhezero.service.impl;

import com.chuhezero.entity.UserEntity;
import com.chuhezero.mapper.BaseMapper;
import com.chuhezero.mapper.master.UserMapper;
import com.chuhezero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<UserEntity> getMapper() {
        return this.userMapper;
    }

}
