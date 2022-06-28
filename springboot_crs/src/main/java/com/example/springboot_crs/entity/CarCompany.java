package com.example.springboot_crs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCompany {


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
