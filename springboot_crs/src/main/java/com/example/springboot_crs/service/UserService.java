package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.UserMapper;
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
}
