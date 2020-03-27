package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthUserInfoVo<T extends Serializable> implements Serializable {
    private String accessToken;
    /**
     * 需要重新登录时间
     */
    private Long expiresIn;
    private T data;
}
