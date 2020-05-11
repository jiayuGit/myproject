package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TFlow {
    private Integer id;

    private String fid;

    private String flowName;

    private String userFid;

    private String remark;

    private Integer state;

    private Integer isDel;

    private Date createTime;

    private Date lastModifyTime;


}