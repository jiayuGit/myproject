package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TMenuAuthorPo {
    private Integer id;

    private String fid;

    private String menuFid;

    private String authFid;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;

    private String authorityName;

    private String authorityPath;


}