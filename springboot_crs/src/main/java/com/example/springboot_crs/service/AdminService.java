package com.example.springboot_crs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springboot_crs.entity.Admin;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.AdminMapper;
import com.example.springboot_crs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;


    public boolean selectAdminAccount(String adminAccount) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminAccount",adminAccount);
        int count = adminMapper.selectCount(wrapper);
        return count == 1;
    }

    public boolean login(String adminAccount, String adminPassword) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminAccount",adminAccount);
        wrapper.eq("adminPassword",adminPassword);

        int count = adminMapper.selectCount(wrapper);
        return count == 1;
    }
    /**
     * @description:  修改user所有内容,包括主键外键
     * @param:
     * @return:
     * @author Hedley
     * @date: 2022-06-29 18:02
     */
    public boolean updateUser(User user) {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("userId",user.getUserId());
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
//        wrapper.eq()
        int insert = userMapper.update(user,wrapper);
        return insert == 1;
    }
}
