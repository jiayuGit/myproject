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
public class TMenuAuthor {
    private Integer id;

    private String fid;

    private String menuFid;

    private String authFid;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;


}