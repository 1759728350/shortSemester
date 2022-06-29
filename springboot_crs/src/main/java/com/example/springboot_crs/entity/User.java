package com.example.springboot_crs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "userId",type = IdType.ASSIGN_UUID)
    private String userId;
    private String userAccount;
    private String userPassword;
    private String userLoc;
    private String userEmail;
    private String userName;
    private String userPhone;
    private String vipId;
    //表示该属性不为数据库表字段，但又是必须使用的。
    @TableField(exist = false)
    private String code;



    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userLoc='" + userLoc + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", vipId='" + vipId + '\'' +
                '}';
    }
}
