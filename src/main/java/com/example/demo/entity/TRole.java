package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TRole {
    private Integer id;

    private String fid;

    private String name;

    private Integer isDel;

    private Date createTime;

    private Date lastModifyTime;


}