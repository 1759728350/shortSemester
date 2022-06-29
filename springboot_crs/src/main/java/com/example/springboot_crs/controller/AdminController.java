package com.example.springboot_crs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_crs.entity.Admin;
import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.mapper.UserMapper;
import com.example.springboot_crs.service.AdminService;
import com.example.springboot_crs.service.UserService;
import com.example.springboot_crs.vo.ErrorCode;
import com.example.springboot_crs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    /**
     * @description: 管理员登录 (查找校验)
     * @param:
     * @return:
     * @author Hedley
     * @date: 2022-06-29 16:26
     */
    @PostMapping("/adminLogin")
    public Result login(@RequestBody Admin admin){
        //查找检验账号是否存在
        boolean isExist = adminService.selectAdminAccount(admin.getAdminAccount());
        if (!isExist){
            return Result.fail(3000,"账户不存在");
        }
        boolean isOk = adminService.login(admin.getAdminAccount(),admin.getAdminPassword());
        if (isOk) {
            //登陆成功
            return Result.success(true);
        }
        //密码错误
        return Result.fail(ErrorCode.PASSWORD_WRONG.getCode(),ErrorCode.PASSWORD_WRONG.getMsg());
    }
    
    /** 
     * @description: 查找成功:返回一个User类型数组查找失败:返回错误码
     * @param:  admin
     * @return:  
     * @author Hedley
     * @date: 2022-06-29 17:23
     */
    @GetMapping("/selectAllUser")
    public Result selectAllUser(){
        List<User> userList = userService.selectAllUser();
        if (userList.isEmpty()){
            return Result.fail(ErrorCode.QUERY_RESULT_IS_EMPTY.getCode(),ErrorCode.QUERY_RESULT_IS_EMPTY.getMsg());
                    
        }
        return Result.success(userList);
    }

    /**
     * @description: 增加用户, 查找成功:返回一个User类型数组,查找失败:返回错误码
     * @param: [user]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-29 17:35
     */
    @PostMapping("/addUser")
    public Result AddUser(@RequestBody User user){
        List<User> users = userService.selectUserByAccount(user.getUserAccount());

        if (!users.isEmpty()){
            return Result.fail(3000,"该账号已存在");
        }
        boolean isOk = userService.addUser(user);
        if (isOk){
            return Result.success(true);
        }
        return Result.fail(3000,"增加失败");
    }
    /**
     * @description: 删除用户
     * @param: [userId]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-29 17:55
     */
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam String userId){
        boolean isOk = userService.deleteUser(userId);
        if (isOk){
            return Result.success(true);
        }
        return Result.fail(3000,"删除失败");

    }
    /**
     * @description: 修改user所有内容,包括主键外键
     * @param: [user]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-29 18:03
     */
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
//        //判断更改的id是否已存在
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("userId",user.getUserId());
//        int count = userMapper.selectCount(wrapper);
//        //若修改后的id已存在
//        if (count > 0){
//            return Result.fail(3000,"id重复");
//        }
        //若id不存在
        boolean isOk = adminService.updateUser(user);
        if (isOk){
            return Result.success(true);
        }
        return Result.fail(3000,"修改失败");
    }




}
