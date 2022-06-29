package com.example.springboot_crs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//声明是一个Mapper接口
@Mapper
public interface UserMapper extends BaseMapper<User> {


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


    @Select("select * from user where userPhone = #{phone}")
    User selectUserByPhone(String userPhone);

    /**
     * @description: 修改用户信息
     * @author Hedley
     * @date: 2022-06-28 14:32
     * @problem: userPhone = #{userPhone}不需要user.userPhone
     */
    @Update("update user set userName = #{userName},userEmail = #{userEmail}," +
            "userLoc = #{userLoc},userPhone = #{userPhone} where userId=#{userId}")
    boolean updateUser(User user);

    @Update("update user set vipId = #{vipId} where userId=#{userId}")
    boolean updateUserVipLevel(@Param("vipId") String vipId,@Param("userId") String userId);


}
