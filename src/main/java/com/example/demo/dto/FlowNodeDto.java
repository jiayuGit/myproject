package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlowNodeDto {


    private String fid;
    private String remark;
    private Integer state;
    private String lastModifyBy;
    private String lastModifyFid;


}
