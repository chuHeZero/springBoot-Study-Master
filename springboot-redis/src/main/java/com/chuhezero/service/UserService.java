package com.chuhezero.service;


import com.chuhezero.entity.UserEntity;


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
     * 查询所有
     *
     * @return
     */
    UserEntity findByUserId(int id);
}
