package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
