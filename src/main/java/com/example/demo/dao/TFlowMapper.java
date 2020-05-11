package com.example.demo.dao;

import com.example.demo.entity.TFlow;
import com.example.demo.entity.TFlowNode;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFlow record);

    int insertSelective(TFlow record);

    TFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFlow record);

    int updateByPrimaryKey(TFlow record);
    List<TFlow> selectByUserFid(String userFid);
    List<TFlow> selectByUserFid1(String flowfid);

    int updateByFlowFid(TFlow record);
}