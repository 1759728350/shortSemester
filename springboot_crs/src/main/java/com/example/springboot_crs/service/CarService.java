package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public List<Car> selectCarByCity(String city) {
        return carMapper.selectCarByCity(city);
    }

    public List<Car> selectCarByCityAndCarType(String city, String carType) {
        return carMapper.selectCarByCityAndCarType(city,carType);
    }
    /**
     * @description:  用户新增个人所属车辆(新增car)
     * @param:
     * @return:
     * @author Hedley
     * @date: 2022-06-29 13:28
     */
    public boolean addCar(Car car) {
        car.setCarId(UUID.randomUUID().toString());
//        return carMapper.insertCar(car);
        int count = carMapper.insert(car);
        return count > 0;

    }

    public boolean deleteCar(String carId) {
        int count = carMapper.deleteById(carId);
        System.out.println("删除返回结果为: "+count);
        return count == 1;
    }

    //用户移除个人车辆
}
