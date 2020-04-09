package com.example.demo.dao;

import com.example.demo.entity.TUserPost;

public interface TUserPostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserPost record);

    int insertSelective(TUserPost record);

    TUserPost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserPost record);

    int updateByPrimaryKey(TUserPost record);
}