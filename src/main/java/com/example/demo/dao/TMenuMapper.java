package com.example.demo.dao;

import com.example.demo.entity.TMenu;
import com.example.demo.entity.TRoleMenu;

import java.util.List;

public interface TMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    TMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);

    List<TMenu> selectMenuPage();
    List<TMenu> selectMenuInfo(TRoleMenu tRoleMenu);
    int updateByFidSelective(TMenu tMenu);
}