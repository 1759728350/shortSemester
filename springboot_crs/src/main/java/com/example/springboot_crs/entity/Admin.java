package com.example.springboot_crs.entity;

public class Admin {
    /*
        author 李梓瑀
        date 2022/6/23
     */

    private String adminAccount;
    private String adminPassword;
    private String adminId;

    public Admin(String adminAccount, String adminPassword, String adminId) {
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminId = adminId;
    }

    public Admin() {
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
