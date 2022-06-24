package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    /*
     * author:李梓瑀
     * data:2022/6/22
     */

    @Autowired(required = false)
    private UserMapper userMapper;
    //查询所有
    public List<User> selectAllUser(){
        List<User> list = userMapper.findAll();
        return list;
    }

    //新增
    public Integer addNewUser(User user) {
        return userMapper.addUser(user);
    }

    //更新
    public Integer updateUser(User user) {
        return userMapper.update(user);
    }

    //删除
    public Integer deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    //通过账号密码查找
    public User selectUserByAccount(String account,String password) {
        return userMapper.findByAccount(account,password);
    }
}
