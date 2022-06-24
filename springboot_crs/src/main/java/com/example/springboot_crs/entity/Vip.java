package com.example.springboot_crs.entity;

public class Vip {
    /*
    data 2022/6/22
    author 李梓瑀
     */

    private String vipId;
    private String vipLevel;

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Vip(String vipId, String vipLevel) {
        this.vipId = vipId;
        this.vipLevel = vipLevel;
    }

    public Vip() {
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vipId='" + vipId + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                '}';
    }

}
