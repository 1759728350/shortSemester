package com.example.springboot_crs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String userAccount;
    private String userPassword;
    private String userLoc;
    private String userEmail;
    private String userName;
    private String userPhone;
    private String vipId;
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
