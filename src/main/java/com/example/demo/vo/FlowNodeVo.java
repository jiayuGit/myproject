package com.example.demo.vo;

import com.example.demo.entity.TFlow;
import com.example.demo.entity.TFlowNode;
import lombok.Data;


@Data
public class FlowNodeVo extends TFlowNode {
    private String userName;
    private String stateName;
    private String flowRemark;
    private String roleName;
}
