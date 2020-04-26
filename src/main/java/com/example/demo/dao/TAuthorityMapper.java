package com.example.demo.dao;

import com.example.demo.entity.TAuthority;

import java.util.List;

public interface TAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthority record);

    int insertSelective(TAuthority record);

    TAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthority record);

    int updateByPrimaryKey(TAuthority record);

    List<TAuthority> selectAuth();

    int updateDeleteByMenuFid(String fid);

    int updateByFidSelective(TAuthority auth);
}