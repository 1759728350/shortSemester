package com.example.springboot_crs.mapper;

import com.example.springboot_crs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//声明是一个Mapper接口
@Mapper
public interface UserMapper {

    /*
     * author:李梓瑀
     * data:2022/6/22
     */

    //查询所有用户
    @Select("select * from user")
    List<User> findAll();

    //增加用户
    @Insert("insert into user values(#{userId},#{userAccount},#{userPassword}" +
            ",#{userLoc},#{userEmail},#{userName},#{userPhone},0)")
    Integer addUser(User user);

    //删除用户
    @Delete("delete from user where userId = #{id}")
    Integer deleteUser(String id);

    //修改用户
    Integer update(User user);

    //通过账号密码查找(此处是基本类型,需使用arg传参)
    @Select("select * from user where userAccount = #{arg0} and userPassword = #{arg1}")
    User findByAccount(String account, String password);

}
