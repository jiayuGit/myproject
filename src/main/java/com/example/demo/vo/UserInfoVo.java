package com.example.demo.vo;

import com.example.demo.entity.TMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private String emaill;

    private String name;

    private List<UserRolePo> rolePoList;
    private List<TMenu> menuList;

    private Long myPending;

    private Long otherPending;



}
