package com.example.demo.dao;

import com.example.demo.entity.TMenuAuthor;
import com.example.demo.vo.TMenuAuthorPo;

import java.util.List;

public interface TMenuAuthorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMenuAuthor record);

    int insertSelective(TMenuAuthor record);

    TMenuAuthor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMenuAuthor record);

    int updateByPrimaryKey(TMenuAuthor record);

    List<TMenuAuthorPo> selectMenuAuthList(List<String> collect);

    int insertList(List<TMenuAuthor> collect);
}