package com.example.demo.dao;

import com.example.demo.entity.TDepartmentPost;

public interface TDepartmentPostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDepartmentPost record);

    int insertSelective(TDepartmentPost record);

    TDepartmentPost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDepartmentPost record);

    int updateByPrimaryKey(TDepartmentPost record);
}