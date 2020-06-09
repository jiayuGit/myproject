package com.example.demo.model;

import com.example.demo.entity.TMenu;
import com.example.demo.vo.UserRolePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserInfoVo implements Serializable {
    private String accessToken;
    /**
     * 需要重新登录时间
     */
    private String fid;
    private Long expiresIn;
    private String name;
    private String emaill;
    private List<UserRolePo> rolePoList;
    private List<TMenu> menuList;


}
