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

    private String phoneNum;
    private String code;
    private int min;

}
