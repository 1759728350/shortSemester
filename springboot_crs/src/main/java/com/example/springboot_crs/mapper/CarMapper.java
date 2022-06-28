package com.example.springboot_crs.mapper;

import com.example.springboot_crs.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper {
    @Select("select * from car where carLoc = #{city}")
    List<Car> selectCarByCity(String city);

    @Select("select * from car where carLoc = #{city} and carType = #{carType}")
    List<Car> selectCarByCityAndCarType(@Param("city") String city, @Param("carType") String carType);
}
