package com.example.demo.entity;

import java.util.Date;

public class TDepartmentPost {
    private Integer id;

    private String fid;

    private String departmentFid;

    private String postFid;

    private Byte isDel;

    private Date createTime;

    private Date lastModifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getDepartmentFid() {
        return departmentFid;
    }

    public void setDepartmentFid(String departmentFid) {
        this.departmentFid = departmentFid == null ? null : departmentFid.trim();
    }

    public String getPostFid() {
        return postFid;
    }

    public void setPostFid(String postFid) {
        this.postFid = postFid == null ? null : postFid.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}