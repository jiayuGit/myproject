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
public class TAuthority {
    private Integer id;

    private String fid;

    private String authorityName;

    private String authorityPath;

    private Integer isDel;

    private Date createTime;

    private Date lastModifyTime;


}