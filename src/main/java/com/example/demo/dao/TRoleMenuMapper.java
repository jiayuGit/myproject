package com.example.demo.dao;

import com.example.demo.entity.TRoleMenu;
import com.example.demo.vo.RoleMenuPo;

import java.util.List;

public interface TRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRoleMenu record);

    int insertSelective(TRoleMenu record);

    TRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRoleMenu record);

    int updateByPrimaryKey(TRoleMenu record);

    List<RoleMenuPo> selectRoleMenuList(List<String> collect);

    int updateDeleteByRoleFid(String roleFid);

    int insertList(List<TRoleMenu> list);
}