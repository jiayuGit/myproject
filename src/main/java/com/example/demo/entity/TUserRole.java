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
public class TUserRole {
    private Integer id;

    private String fid;

    private String userFid;

    private String roleFid;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;


}