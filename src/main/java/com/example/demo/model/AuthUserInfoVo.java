package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthUserInfoVo implements Serializable {
    private String accessToken;
    /**
     * 需要重新登录时间
     */
    private Long expiresIn;
    private String name;

}
