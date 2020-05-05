package com.example.demo.dto;

import com.example.demo.entity.TFlowNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FlowInfoDto implements Serializable {
    private String flowName;
    private String remark;
    private String userFid;
    private List<TFlowNode> list;
}
