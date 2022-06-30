package com.example.springboot_crs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_crs.entity.CarCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<CarCompany> {

    @Select("select * from carcompany where carCLoc = #{city}")
    List<CarCompany> selectCompanyByCity(String city);
}
