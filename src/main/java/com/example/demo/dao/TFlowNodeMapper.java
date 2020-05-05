package com.example.demo.dao;

import com.example.demo.entity.TFlowNode;
import com.example.demo.vo.FlowNodeVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TFlowNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFlowNode record);

    int insertSelective(TFlowNode record);

    TFlowNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFlowNode record);

    int updateByPrimaryKey(TFlowNode record);

    int updateByFlowFid(TFlowNode record);

    List<FlowNodeVo> selectFlowNode(List<String> list);
}