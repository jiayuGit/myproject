package com.example.demo.dao;

import com.example.demo.entity.TPost;

public interface TPostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPost record);

    int insertSelective(TPost record);

    TPost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPost record);

    int updateByPrimaryKey(TPost record);
}