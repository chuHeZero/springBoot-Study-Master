package com.chuhe.mapper;

import com.chuhe.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into t_user(id,name,password,age) values (#{id},#{name},#{password},#{age})")
    void addUser(UserEntity user);


    @Update("update t_user set name=#{name},password=#{password}, age=#{age} where id=#{id}")
    void updateUser(UserEntity user);


    @Delete("delete from t_user where id=#{id}")
    void deleteUser(int id);

    @Select("SELECT * FROM t_user where id=#{id}")
    UserEntity findById(int id);


    @Select("SELECT * FROM t_user")
    List<UserEntity> findAll();
}
