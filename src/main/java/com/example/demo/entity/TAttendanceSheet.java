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
public class TAttendanceSheet {
    private Integer id;

    private String fid;

    private String userFid;

    private Date startTime;

    private Date endTime;

    private Byte isVacte;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;

    private Date start;
    private Date end;

}