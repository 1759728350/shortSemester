package com.example.springboot_crs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_crs.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
