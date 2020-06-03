package com.chuhe.service.impl;

import java.util.List;

import com.chuhe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuhe.mapper.UserMapper;
import com.chuhe.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean addUser(UserEntity userEntity) {
        boolean flag = false;
        try {
            userMapper.save(userEntity);
            flag = true;
        } catch (Exception e) {
            System.out.println("新增失败!");
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public boolean updateUser(UserEntity userEntity) {
        boolean flag = false;
        try {
            userMapper.save(userEntity);
            flag = true;
        } catch (Exception e) {
            System.out.println("修改失败!");
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public boolean deleteUser(Long id) {
        boolean flag = false;
        try {
            userMapper.delete(id);
            flag = true;
        } catch (Exception e) {
            System.out.println("删除失败!");
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public UserEntity findUserById(Long id) {
        return userMapper.findOne(id);
    }


    @Override
    public List<UserEntity> findAll() {
        return userMapper.findAll();
    }

}
