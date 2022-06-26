package com.example.springboot_crs.controller;

import cn.hutool.json.JSONObject;
import com.example.springboot_crs.entity.Sms;
import com.example.springboot_crs.utils.VerificationCodeUtils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sms")
@CrossOrigin
public class SmsController {
    /**
     * @description: 发送验证码
     * @param: [phoneNum] 手机号
     * @return: void
     * @author Hedley
     * @date: 2022-06-26 12:03
     */
    @PostMapping("/sendCode")
    public void sms(@RequestBody JSONObject jsonObject){

        Sms sms = new Sms(jsonObject.getStr("phoneNum"), VerificationCodeUtils.generateCode(), 1);

        System.out.println(sms.getPhoneNum());
        int appid = 1400697146;
        String appKey = "0b6eead2fbc17e219e856f238f5a089c";
        int templateId = 1452369;
        String smsSign = "雁南飞无留意";

        String[] params = {sms.getCode()};
        SmsSingleSender sender = new SmsSingleSender(appid, appKey);
        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", sms.getPhoneNum(),
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
