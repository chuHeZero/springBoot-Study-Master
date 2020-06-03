package com.chuhe.service;

import java.util.List;

import com.chuhe.entity.UserEntity;


public interface UserService {

    /**
     * 新增用户
     *
     * @param userEntity
     * @return
     */
    boolean addUser(UserEntity userEntity);


    /**
     * 修改用户
     *
     * @param userEntity
     * @return
     */
    boolean updateUser(UserEntity userEntity);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(Long id);


    /**
     * 根据用户名字查询用户信息
     *
     * @param id
     */
    UserEntity findUserById(Long id);


    /**
     * 查询所有
     *
     * @return
     */
    List<UserEntity> findAll();

}
