package com.chuhezero.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chuhezero.entity.UserEntity;


@Mapper
public interface UserMapper {


    @Insert("insert into t_user(id,name,age) values (#{id},#{name},#{age})")
    void insert(UserEntity userEntity);

    @Update("update t_user set name=#{name}, age=#{age} where id=#{id}")
    void update(UserEntity userEntity);

    @Select("SELECT * FROM t_user where id=#{id}")
    UserEntity findById(long id);

}
