package com.example.springboot_crs.entity;

public class CarCompany {
    /*
    author 李梓瑀
    date 2022/6/23
     */

    private String carCid;
    private String carCname;
    private String carLoc;
    private String carEmail;
    private String carPhone;

    public CarCompany(String carCid, String carCname, String carLoc, String carEmail, String carPhone) {
        this.carCid = carCid;
        this.carCname = carCname;
        this.carLoc = carLoc;
        this.carEmail = carEmail;
        this.carPhone = carPhone;
    }

    public CarCompany() {
    }

    public String getCarCid() {
        return carCid;
    }

    public void setCarCid(String carCid) {
        this.carCid = carCid;
    }

    public String getCarCname() {
        return carCname;
    }

    public void setCarCname(String carCname) {
        this.carCname = carCname;
    }

    public String getCarLoc() {
        return carLoc;
    }

    public void setCarLoc(String carLoc) {
        this.carLoc = carLoc;
    }

    public String getCarEmail() {
        return carEmail;
    }

    public void setCarEmail(String carEmail) {
        this.carEmail = carEmail;
    }

    public String getCarPhone() {
        return carPhone;
    }

    public void setCarPhone(String carPhone) {
        this.carPhone = carPhone;
    }

    @Override
    public String toString() {
        return "CarCompany{" +
                "carCid='" + carCid + '\'' +
                ", carCname='" + carCname + '\'' +
                ", carLoc='" + carLoc + '\'' +
                ", carEmail='" + carEmail + '\'' +
                ", carPhone='" + carPhone + '\'' +
                '}';
    }
}
