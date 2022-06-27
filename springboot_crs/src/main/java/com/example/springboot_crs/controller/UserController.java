package com.example.springboot_crs.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.service.UserService;
import com.example.springboot_crs.utils.VerificationCodeUtils;
import com.example.springboot_crs.vo.ErrorCode;
import com.example.springboot_crs.vo.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @description:
     * @param: [userVo]
     * @return: com.example.springboot_crs.entity.User
     * @author Hedley
     * @date: 2022-06-24 14:56
     */
    @PostMapping("/login")
    public Result login(@RequestBody User userInfo) {
        String account = userInfo.getUserAccount();
        String password = userInfo.getUserPassword();
        //判断账号密码是否为空
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password)) {

            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),
                    ErrorCode.PARAMS_ERROR.getMsg());
        }
        List<User> userList = userService.selectUserByAccount(account);

        for (User user : userList) {

            if (user.getUserPassword().equals(password)) {
                return Result.success(user);
            }

        }
        //null返回结果是1,因此用Result返回解决
        return Result.fail(ErrorCode. QUERY_RESULT_IS_EMPTY.getCode(),
                ErrorCode. QUERY_RESULT_IS_EMPTY.getMsg());
    }
    /** 
     * @description:  
     * @param:  
     * @return:  
     * @author Hedley
     * @date: 2022-06-27 14:58
     */ 
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        String phone = user.getUserPhone();
        String code = user.getCode();
        //验证码验证
        if (code.equals(VerificationCodeUtils.smsCode)){
            //账号是否已注册查询
            List<User> users = userService.selectUserByAccount(account);
            if (users.isEmpty()){
                //注册账号
                boolean registerOk = userService.registerUserAccount(account, password, phone);
                if (registerOk){
                    return Result.success(true);
                }
                //数据库异常
                return Result.fail(ErrorCode.DATABASE_SQL_ERROR.getCode(), ErrorCode.DATABASE_SQL_ERROR.getMsg());

            }
            return Result.fail(3000,"账号已存在");
        }
        //验证码验证失败
        return Result.fail(ErrorCode.VERIFICATION_CODE_ERROR.getCode(), ErrorCode.VERIFICATION_CODE_ERROR.getMsg());

    }

}
