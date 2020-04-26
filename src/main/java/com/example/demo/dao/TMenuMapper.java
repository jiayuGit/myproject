package com.example.demo.dao;

import com.example.demo.entity.TMenu;

import java.util.List;

public interface TMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    TMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);

    List<TMenu> selectMenuPage();

    int updateByFidSelective(TMenu tMenu);
}