package com.chuhe.service.impl;

import com.chuhe.entity.UserEntity;
import com.chuhe.mapper.UserMapper;
import com.chuhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean addUser(UserEntity user) {
        boolean flag = false;
        try {
            userMapper.addUser(user);
            flag = true;
        } catch (Exception e) {
            System.out.println("新增失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(UserEntity user) {
        boolean flag = false;
        try {
            userMapper.updateUser(user);
            flag = true;
        } catch (Exception e) {
            System.out.println("修改失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        try {
            userMapper.deleteUser(id);
            flag = true;
        } catch (Exception e) {
            System.out.println("删除失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public UserEntity findUserById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userMapper.findAll();
    }
}
