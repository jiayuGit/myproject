package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TUserPost {
    private Integer id;

    private String fid;

    private String userFid;

    private String userName;

    private String postFid;

    private String postName;

    private Byte isLeader;

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

    public String getUserFid() {
        return userFid;
    }

    public void setUserFid(String userFid) {
        this.userFid = userFid == null ? null : userFid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPostFid() {
        return postFid;
    }

    public void setPostFid(String postFid) {
        this.postFid = postFid == null ? null : postFid.trim();
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Byte getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Byte isLeader) {
        this.isLeader = isLeader;
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