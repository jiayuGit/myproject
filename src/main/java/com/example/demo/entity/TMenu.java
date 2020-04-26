package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TMenu {
    private Integer id;

    private String fid;

    private String menuName;

    private Integer isDel;

    private Date createTime;

    private Date lastModifyTime;


}