package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.UserMapper;
import com.example.springboot_crs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {



    @Autowired
    private UserMapper userMapper;

    public List<User> selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    public boolean registerUserAccount(String userAccount, String userPassword, String userPhone) {
        String userId = UUID.randomUUID().toString();
        System.out.println("生成用户id为"+userId);
        return userMapper.insertUser(userId,userAccount,userPassword,userPhone);
    }
    /**
     * @description: 根据手机号查询user信息
     * @param:
     * @return:  User
     * @author Hedley
     * @date: 2022-06-27 18:49
     */
    public User selectUserByPhone(String userPhone) {

        return userMapper.selectUserByPhone(userPhone);
    }
    /**
     * @description: 查询该手机号是否被注册
     * @param:
     * @return: boolean
     * @author Hedley
     * @date: 2022-06-27 18:28
     */
    public boolean selectPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        return user != null;
    }

    public boolean updateUser(User user) {

        return userMapper.updateUser(user);

    }

    public boolean updateUserVipLevel(String vipId, String userId) {
        return userMapper.updateUserVipLevel(vipId,userId);
    }
}
