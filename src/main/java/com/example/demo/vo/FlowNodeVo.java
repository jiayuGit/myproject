package com.example.demo.vo;

import com.example.demo.entity.TFlow;
import lombok.Data;


@Data
public class FlowNodeVo extends FlowVo {
    private String userName;
    private String stateName;
}
