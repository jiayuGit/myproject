package com.example.demo.dao;

import com.example.demo.entity.TAttendanceSheet;

public interface TAttendanceSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAttendanceSheet record);

    int insertSelective(TAttendanceSheet record);

    TAttendanceSheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAttendanceSheet record);

    int updateByPrimaryKey(TAttendanceSheet record);

    void updateByStartTime(TAttendanceSheet tAttendanceSheet);

    TAttendanceSheet selectByTime(TAttendanceSheet tAttendanceSheet);
}