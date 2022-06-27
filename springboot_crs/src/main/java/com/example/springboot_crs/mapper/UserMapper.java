package com.example.springboot_crs.mapper;

import com.example.springboot_crs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//声明是一个Mapper接口
@Mapper
public interface UserMapper {


    @Select("select * from user where userAccount = #{account}")
    List<User> selectUserByAccount(String account);
    /**
     * @description:
     * @author Hedley
     * @date: 2022-06-27 16:01
     * @problem: mybatis异常BindingException:    Parameter 'userId' not found. Available parameters are [arg1, arg0, param1, param2]
     *      加@Param("userPassword")
     */
    @Insert("insert into user (userId,userAccount,userPassword,userPhone) values (#{userId},#{userAccount},#{userPassword},#{userPhone})")
    boolean insertUser(@Param("userId")String userId,@Param("userAccount")String userAccount
            ,@Param("userPassword")String userPassword, @Param("userPhone")String userPhone);
}
