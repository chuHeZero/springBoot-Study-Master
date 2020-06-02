package com.chuhe.service;

import com.chuhe.entity.UserEntity;

import java.util.List;

public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean addUser(UserEntity user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    boolean updateUser(UserEntity user);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 根据用户名字查询用户信息
     *
     * @param id
     */
    UserEntity findUserById(int id);

    /**
     * 查询所有
     *
     * @return
     */
    List<UserEntity> findAll();
}
