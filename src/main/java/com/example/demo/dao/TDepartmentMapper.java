package com.example.demo.dao;

import com.example.demo.entity.TDepartment;

public interface TDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDepartment record);

    int insertSelective(TDepartment record);

    TDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDepartment record);

    int updateByPrimaryKey(TDepartment record);
}