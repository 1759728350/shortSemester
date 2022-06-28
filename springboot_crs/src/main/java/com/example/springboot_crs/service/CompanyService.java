package com.example.springboot_crs.service;

import com.example.springboot_crs.entity.CarCompany;
import com.example.springboot_crs.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public List<CarCompany> selectCompanyByCity(String city) {
        List<CarCompany> carCompanyList = companyMapper.selectCompanyByCity(city);
        return carCompanyList;
    }
}
