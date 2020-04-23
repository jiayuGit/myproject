package com.example.demo.dao;

import com.example.demo.entity.TUserRole;
import com.example.demo.vo.KeyValueVo;
import com.example.demo.vo.UserRolePo;

import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);

    List<UserRolePo> selectUserRoleIfList(List<String> list);
}