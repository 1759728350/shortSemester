package com.example.springboot_crs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("carcompany")
public class CarCompany {

    @TableId(value = "carCid",type = IdType.ASSIGN_UUID)
    private String carCid;
    private String carCname;
    private String carCLoc;
    private String carCEmail;
    private String carPhone;
    private String carCInfo;


    @Override
    public String toString() {
        return "CarCompany{" +
                "carCid='" + carCid + '\'' +
                ", carCname='" + carCname + '\'' +
                ", carCLoc='" + carCLoc + '\'' +
                ", carCEmail='" + carCEmail + '\'' +
                ", carPhone='" + carPhone + '\'' +
                ", carCInfo='" + carCInfo + '\'' +
                '}';
    }
}
