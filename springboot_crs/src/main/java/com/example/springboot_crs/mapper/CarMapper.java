package com.example.springboot_crs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_crs.entity.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper extends BaseMapper<Car> {
    @Select("select * from car where carLoc = #{city}")
    List<Car> selectCarByCity(String city);

    @Select("select * from car where carLoc = #{city} and carType = #{carType}")
    List<Car> selectCarByCityAndCarType(@Param("city") String city, @Param("carType") String carType);


    //    @Insert("insert into car (carId,carModel,carNumber,carColor,carImg,carType,useTime,Mileage,state,LeaseAmount,overdueAmount,startTime,endTime,deposit,carBrand,userID,carCid,LeaseholderID,carLoc,carInfo) value (#{carId},#{carModel},#{carNumber},#{carColor},#{carImg},#{carType},#{useTime},#{Mileage},#{state},#{LeaseAmount},#{overdueAmount},#{startTime},#{endTime},#{deposit},#{carBrand},#{userID},#{carCid},#{LeaseholderID},#{carLoc},#{carInfo})"
//    boolean insertCar(Car car);
//    boolean insertCar(Car car);
}
