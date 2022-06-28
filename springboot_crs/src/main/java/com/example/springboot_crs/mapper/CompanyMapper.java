package com.example.springboot_crs.mapper;

import com.example.springboot_crs.entity.CarCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Select("select * from carcompany where carCLoc = #{city}")
    List<CarCompany> selectCompanyByCity(String city);
}
