package com.example.springboot_crs.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.service.UserService;
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
    @GetMapping("/login")
    public Result login(@RequestParam("userAccount") String account,
                        @RequestParam("userPassword") String password) {

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

}
