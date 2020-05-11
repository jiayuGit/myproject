package com.example.demo.vo;

import com.example.demo.entity.TFlow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class FlowVo extends TFlow {
    private String stateName;
}
