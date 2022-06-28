package com.example.springboot_crs.controller;

import com.example.springboot_crs.entity.Car;
import com.example.springboot_crs.service.CarService;
import com.example.springboot_crs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * @description: 按城市地址查找租借车辆信息(查找表中地址为请求参数的汽车信息
     * @param: [myCity]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-28 9:35
     */
    @GetMapping("/city")
    public Result selectCarByCity(@RequestParam String myCity){
        List<Car> carList = carService.selectCarByCity(myCity);
        return Result.success(carList);
    }

    /**
     * @description: 按照汽车所属和地址类型查找汽车(查找表中地址和所属类型为请求参数的汽车信息)
     * @param: [myCity, carType]
     * @return: com.example.springboot_crs.vo.Result
     * @author Hedley
     * @date: 2022-06-28 14:14
     */
    @GetMapping("/cityAndCarType")
    public Result selectCarByCityAndCarType(@RequestParam String myCity,@RequestParam String carType){
        List<Car> carList = carService.selectCarByCityAndCarType(myCity,carType);
        return Result.success(carList);
    }

}
