package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TFlowNode {
    private Integer id;

    private String fid;

    private String nodeName;

    private String flowFid;

    private String roleFid;

    private String remark;

    private Integer state;

    private String parentFid;

    private String nextFid;

    private Integer isDel;

    private String lastModifyBy;

    private String lastModifyFid;

    private Date createTime;

    private Date lastModifyTime;


}