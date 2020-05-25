package com.chuhezero.service.impl;

import com.chuhezero.dao.UserDao;
import com.chuhezero.entity.UserEntity;
import com.chuhezero.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserDao userDao;


    @Override
    public boolean addUser(UserEntity user) {
        boolean flag = false;
        try {
            userDao.addUser(user);
            flag = true;
        } catch (Exception e) {
            logger.error("新增失败!", e);
        }
        return flag;
    }


    @Override
    public boolean updateUser(UserEntity user) {
        boolean flag = false;
        try {
            userDao.updateUser(user);
            flag = true;
        } catch (Exception e) {
            logger.error("修改失败!", e);
        }
        return flag;
    }


    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        try {
            userDao.deleteUser(id);
            flag = true;
        } catch (Exception e) {
            logger.error("删除失败!", e);
        }
        return flag;
    }


    @Override
    public UserEntity findByUserId(int id) {
        return userDao.findByUserId(id);
    }
}
