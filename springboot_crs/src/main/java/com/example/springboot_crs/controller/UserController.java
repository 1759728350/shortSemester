package com.example.springboot_crs.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.entity.User;
import com.example.springboot_crs.service.UserService;
import com.example.springboot_crs.utils.VerificationCodeUtils;
import com.example.springboot_crs.vo.ErrorCode;
import com.example.springboot_crs.vo.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @description: 使用账号密码与数据库校验
     * @param: [userVo]
     * @return: com.example.springboot_crs.entity.User
     * @author Hedley
     * @date: 2022-06-24 14:56
     */
    @PostMapping("/login")
    public Result login(@RequestBody User userInfo, HttpSession session) {
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
                //session存储userId
                session.setAttribute("userId",user.getUserId());
                return Result.success(user);
            }

        }
        //null返回结果是1,因此用Result返回解决
        return Result.fail(ErrorCode.QUERY_RESULT_IS_EMPTY.getCode(),
                ErrorCode.QUERY_RESULT_IS_EMPTY.getMsg());
    }

    /**
     * @description: 使用短信验证码和验证码进行校验
     * @param: 电话,验证码
     * @return:
     * @author Hedley
     * @date: 2022-06-27 17:00
     */
    @PostMapping("/loginByPhone")
    public Result loginByPhone(@RequestBody JSONObject jsonObject) {
        String userPhone = jsonObject.getStr("userPhone");
        String code = jsonObject.getStr("code");

        if (code.equals(VerificationCodeUtils.smsCode)){
            User user = userService.selectUserByPhone(userPhone);
            return Result.success(user);

        }
        return Result.fail(ErrorCode.VERIFICATION_CODE_ERROR.getCode(), ErrorCode.VERIFICATION_CODE_ERROR.getMsg());

    }


    /**
     * @description: 使用账号, 密码, 和手机号, 验证码进行校验, 若校验账号和手机均为未注册, 则新增该用户账号
     * @param: 账号, 密码, 电话, 验证码
     * @return: boolean
     * @author Hedley
     * @date: 2022-06-27 14:58
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        String phone = user.getUserPhone();
        String code = user.getCode();
        //验证码验证
        if (code.equals(VerificationCodeUtils.smsCode)) {
            //账号是否已注册查询
            List<User> users = userService.selectUserByAccount(account);
            if (users.isEmpty()) {
                //该手机号是否已被注册
                boolean isExist = userService.selectPhone(phone);
                if (isExist){
                    return Result.fail(3000,"该手机号已被注册");
                }
                //注册账号
                boolean registerOk = userService.registerUserAccount(account, password, phone);
                if (registerOk) {
                    return Result.success(true);
                }
                //数据库异常
                return Result.fail(ErrorCode.DATABASE_SQL_ERROR.getCode(), ErrorCode.DATABASE_SQL_ERROR.getMsg());

            }
            return Result.fail(3000, "账号已存在");
        }
        //验证码验证失败
        return Result.fail(ErrorCode.VERIFICATION_CODE_ERROR.getCode(), ErrorCode.VERIFICATION_CODE_ERROR.getMsg());

    }
    /**
     * @description: 个人信息修改(修改用户个人信息)
     * @param: 用户名,邮箱,地址,手机号
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-28 13:43
     */
    @PutMapping("/updateInfo")
    public Result updateUser(@RequestBody User user,HttpSession session){
        String userId = session.getAttribute("userId").toString();
        System.out.println("获取的session userId为"+userId);
        user.setUserId(userId);
        boolean isOk = userService.updateUser(user);
        if (isOk){
            return Result.success(true);
        }
        return Result.fail(3000,"修改失败");
    }
    /**
     * @description: 用户办理vip ,(修改用户表中vipId对应的等级)
     * @param: [jsonObject]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-28 14:57
     */
    @PutMapping("/updateVipLevel")
    public Result updateUserVipLevel(@RequestBody JSONObject jsonObject){
        String vipId = jsonObject.getStr("vipId");
        String userId = jsonObject.getStr("userId");
        boolean isOk = userService.updateUserVipLevel(vipId,userId);
        if (isOk){
            return Result.success(true);
        }
        return Result.fail(3000,"修改失败");
    }


}
