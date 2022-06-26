package com.example.springboot_crs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 短信信息实体
 * @author Hedley
 * @date: 2022-06-26 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {
    //手机号
    private String phoneNum;
    //验证码
    private String code;
    //过期重发时间
    private int min;

}
