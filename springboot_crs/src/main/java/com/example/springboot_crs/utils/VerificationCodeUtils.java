package com.example.springboot_crs.utils;

import java.util.Random;

public class VerificationCodeUtils {
    /**
     * @description:  随机生成6位数字验证码
     * @param:  null
     * @return:  String
     * @author Hedley
     * @date: 2022-06-26 13:15
     */
    public static String generateCode(){
        StringBuilder code = new StringBuilder(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            //拼接6位1到9的随机数
            code.append(random.nextInt(10));
        }
        System.out.println("生成验证码为: "+code.toString());
        return code.toString();
    }
    public static void main(String[] args) {
        String s = VerificationCodeUtils.generateCode();
        System.out.println(s);
    }
}


