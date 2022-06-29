package com.example.springboot_crs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_crs.entity.Car;
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

    /** 
     * @description: 查询所有用户给admin 
     * @param:  
     * @return:  
     * @author Hedley
     * @date: 2022-06-29 16:33
     */ 
    public List<User> selectAllUser() {

        List<User> users = userMapper.selectList(null);
        return users;
    }
    /**
     * @description:  admin 增加用户
     * @param:  user
     * @return:
     * @author Hedley
     * @date: 2022-06-29 17:44
     */
    public boolean addUser(User user) {

        //设置vip默认为0
        user.setVipId("0");
        int count = userMapper.insert(user);
        return count == 1;

    }

    public boolean deleteUser(String userId) {
        int count = userMapper.deleteById(userId);
        return count == 1;
    }

    //update 放到了adminService中,以防与本身的userUpdate冲突


}
