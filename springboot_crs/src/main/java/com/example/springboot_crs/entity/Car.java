package com.example.springboot_crs.entity;

import java.sql.Timestamp;

public class Car {

  private String carId;
  private String carModel;
  private String carNumber;
  private String carColor;
  private String carImg;
  private long carType;
  private double useTime;
  private double mileage;
  private long state;
  private double leaseAmount;
  private double overdueAmount;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private double deposit;
  private String carBrand;
  private String userId;
  private String carCid;
  private String leaseholderId;
  private String carLoc;
  private String carInfo;

  public Car(String carId, String carModel, String carNumber,
             String carColor, String carImg, long carType, double useTime, double mileage,
             long state, double leaseAmount, double overdueAmount, Timestamp startTime, Timestamp endTime,
             double deposit, String carBrand, String userId, String carCid, String leaseholderId, String carLoc, String carInfo) {
    this.carId = carId;
    this.carModel = carModel;
    this.carNumber = carNumber;
    this.carColor = carColor;
    this.carImg = carImg;
    this.carType = carType;
    this.useTime = useTime;
    this.mileage = mileage;
    this.state = state;
    this.leaseAmount = leaseAmount;
    this.overdueAmount = overdueAmount;
    this.startTime = startTime;
    this.endTime = endTime;
    this.deposit = deposit;
    this.carBrand = carBrand;
    this.userId = userId;
    this.carCid = carCid;
    this.leaseholderId = leaseholderId;
    this.carLoc = carLoc;
    this.carInfo = carInfo;
  }

  public String getCarInfo() {
    return carInfo;
  }

  public void setCarInfo(String carInfo) {
    this.carInfo = carInfo;
  }

  public String getCarId() {
    return carId;
  }

  public void setCarId(String carId) {
    this.carId = carId;
  }


  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }


  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }


  public String getCarColor() {
    return carColor;
  }

  public void setCarColor(String carColor) {
    this.carColor = carColor;
  }


  public String getCarImg() {
    return carImg;
  }

  public void setCarImg(String carImg) {
    this.carImg = carImg;
  }


  public long getCarType() {
    return carType;
  }

  public void setCarType(long carType) {
    this.carType = carType;
  }


  public double getUseTime() {
    return useTime;
  }

  public void setUseTime(double useTime) {
    this.useTime = useTime;
  }


  public double getMileage() {
    return mileage;
  }

  public void setMileage(double mileage) {
    this.mileage = mileage;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public double getLeaseAmount() {
    return leaseAmount;
  }

  public void setLeaseAmount(double leaseAmount) {
    this.leaseAmount = leaseAmount;
  }


  public double getOverdueAmount() {
    return overdueAmount;
  }

  public void setOverdueAmount(double overdueAmount) {
    this.overdueAmount = overdueAmount;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public double getDeposit() {
    return deposit;
  }

  public void setDeposit(double deposit) {
    this.deposit = deposit;
  }


  public String getCarBrand() {
    return carBrand;
  }

  public void setCarBrand(String carBrand) {
    this.carBrand = carBrand;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getCarCid() {
    return carCid;
  }

  public void setCarCid(String carCid) {
    this.carCid = carCid;
  }


  public String getLeaseholderId() {
    return leaseholderId;
  }

  public void setLeaseholderId(String leaseholderId) {
    this.leaseholderId = leaseholderId;
  }


  public String getCarLoc() {
    return carLoc;
  }

  public void setCarLoc(String carLoc) {
    this.carLoc = carLoc;
  }

}
