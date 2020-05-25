package com.chuhezero.dao;

import com.chuhezero.entity.UserEntity;

public interface UserDao {
   
    /**
     * 用户数据新增
     */
    void addUser(UserEntity user);


    /**
     * 用户数据修改
     */
    void updateUser(UserEntity user);


    /**
     * 用户数据删除
     */
    void deleteUser(int id);


    /**
     * 查询
     */
    UserEntity findByUserId(int id);
}
