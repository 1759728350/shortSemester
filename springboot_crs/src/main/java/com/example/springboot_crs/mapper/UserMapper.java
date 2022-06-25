package com.example.springboot_crs.mapper;

import com.example.springboot_crs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//声明是一个Mapper接口
@Mapper
public interface UserMapper {


    @Select("select * from user where userAccount = #{account}")
    List<User> selectUserByAccount(String account);
}
