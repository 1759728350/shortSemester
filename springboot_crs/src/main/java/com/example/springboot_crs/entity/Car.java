package com.example.springboot_crs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  //mybatis主键自动生成策略
  @TableId(value = "carId",type = IdType.ASSIGN_UUID)
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


}
