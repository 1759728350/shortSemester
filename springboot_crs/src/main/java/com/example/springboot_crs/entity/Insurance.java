package com.example.springboot_crs.entity;

import java.util.Date;

public class Insurance {
    /*
        author 李梓瑀
        date 2022/6/23
     */
    private String insuranceID;
    private String insuranceName;
    private Date insuranceDate;
    private Date insurancePrice;
    private Date insuredAmount;
    private String Cid;

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Date insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Date getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(Date insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public Insurance(String insuranceID, String insuranceName, Date insuranceDate, Date insurancePrice, Date insuredAmount, String cid) {
        this.insuranceID = insuranceID;
        this.insuranceName = insuranceName;
        this.insuranceDate = insuranceDate;
        this.insurancePrice = insurancePrice;
        this.insuredAmount = insuredAmount;
        Cid = cid;
    }

    public Insurance() {
    }

    @Override
    public String toString() {
        return "insurance{" +
                "insuranceID='" + insuranceID + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", insuranceDate=" + insuranceDate +
                ", insurancePrice=" + insurancePrice +
                ", insuredAmount=" + insuredAmount +
                ", Cid='" + Cid + '\'' +
                '}';
    }
}
