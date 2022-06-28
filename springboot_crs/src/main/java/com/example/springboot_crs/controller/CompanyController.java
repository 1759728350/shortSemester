package com.example.springboot_crs.controller;

import com.example.springboot_crs.entity.CarCompany;
import com.example.springboot_crs.service.CompanyService;
import com.example.springboot_crs.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    /** 
     * @description:  按城市地址查找汽车中介公司(查找表中地址为请求参数的汽车中介公司信息
     * @param:  
     * @return:  
     * @author Hedley
     * @date: 2022-06-27 16:38
     */
    @GetMapping("/city")
    public Result selectCompanyByCity(@RequestParam String myCity){
        List<CarCompany> carCompany = companyService.selectCompanyByCity(myCity);
        return Result.success(carCompany);

    }
}
