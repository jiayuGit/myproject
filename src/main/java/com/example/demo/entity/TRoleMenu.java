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
public class TRoleMenu {
    private Integer id;

    private String fid;

    private String roleFid;

    private String menuFid;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;


}