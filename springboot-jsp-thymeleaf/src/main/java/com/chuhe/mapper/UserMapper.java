package com.chuhe.mapper;

import com.chuhe.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;


@Mapper
public interface UserMapper extends JpaRepository<UserEntity, Long> {

}
