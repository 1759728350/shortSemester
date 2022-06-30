package com.example.springboot_crs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springboot_crs.entity.Admin;
import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.entity.CarCompany;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.AdminMapper;
import com.example.springboot_crs.mapper.CarMapper;
import com.example.springboot_crs.mapper.CompanyMapper;
import com.example.springboot_crs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CompanyMapper companyMapper;

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

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        int update = userMapper.updateById(user);
        return update == 1;
    }

    public boolean updateCar(Car car) {

        int count = carMapper.updateById(car);
        return count >= 1;
    }

    public boolean updateCompany(CarCompany company) {
        int count = companyMapper.updateById(company);
        return count >= 1;
    }
}
