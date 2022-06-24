package com.example.springboot_crs.controller;

import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@CrossOrigin解决前端跨域问题
@CrossOrigin
public class UserController {

    /*
    * author:李梓瑀
    * data:2022/6/22
    */

    @Autowired(required = false)
    private UserService service;

    //查询所有用户
    @GetMapping("/findAll")
    public List<User> selectAll(){
        System.out.println("查询所有");
        return service.selectAllUser();
    }

    //新增及修改新用户
    @PostMapping("/addUser")
    public Integer save(@RequestBody User user){
        System.out.println("新增用户");
        return service.addNewUser(user);
    }

    //更新用户
    @PutMapping("/updateUser")
    public Integer update(@RequestBody User user){
        System.out.println("更新用户");
        return service.updateUser(user);
    }

    //删除用户
    @DeleteMapping("/deleteUser/{id}")
    public Integer delete(@PathVariable("id") String id){
        System.out.println("删除用户");
        System.out.println("id为"+id);
        return service.deleteUser(id);
    }

    //按照用户账号和密码查找用户
    @GetMapping("/findUserByAccount")
    public User loginByAccount(@RequestParam("account") String account,@RequestParam("password") String password){
        System.out.println("通过账号密码查找用户");
        return service.selectUserByAccount(account,password);
    }


}
