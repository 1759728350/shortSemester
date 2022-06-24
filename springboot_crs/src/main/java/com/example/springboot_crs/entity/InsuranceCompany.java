package com.example.springboot_crs.entity;

public class InsuranceCompany {
    /*
     author 李梓瑀
     date 2022/6/23
     */
    private String Cid;
    private String Cname;
    private String CLoc;
    private String CEmail;
    private String CPhone;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCLoc() {
        return CLoc;
    }

    public void setCLoc(String CLoc) {
        this.CLoc = CLoc;
    }

    public String getCEmail() {
        return CEmail;
    }

    public void setCEmail(String CEmail) {
        this.CEmail = CEmail;
    }

    public String getCPhone() {
        return CPhone;
    }

    public void setCPhone(String CPhone) {
        this.CPhone = CPhone;
    }

    public InsuranceCompany() {
    }

    public InsuranceCompany(String cid, String cname, String CLoc, String CEmail, String CPhone) {
        Cid = cid;
        Cname = cname;
        this.CLoc = CLoc;
        this.CEmail = CEmail;
        this.CPhone = CPhone;
    }

    @Override
    public String toString() {
        return "InsuranceCompany{" +
                "Cid='" + Cid + '\'' +
                ", Cname='" + Cname + '\'' +
                ", CLoc='" + CLoc + '\'' +
                ", CEmail='" + CEmail + '\'' +
                ", CPhone='" + CPhone + '\'' +
                '}';
    }
}
