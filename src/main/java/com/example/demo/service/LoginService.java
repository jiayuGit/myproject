package com.example.demo.service;

import com.example.demo.dao.TUserMapper;
import com.example.demo.entity.TUser;
import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.model.Result;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LoginService {
    @Autowired
    private TUserMapper tUserMapper;

    @Transactional
    public String addUser(TUser user) {
        tUserMapper.insert(user);
        return "添加成功";

    }


    public List<TUser> selectUserPage() {
        return tUserMapper.selectUserPage();
    }

    public Result login(TUser user) {
        List<TUser> tUsers = tUserMapper.selectByUser(user);
        if (Check.NuNCollection(tUsers)){
            return Result.fail("账号不存在或密码错误");
        }
        AuthUserInfoVo userInfoVo = AuthUserInfoVo.builder()
                .accessToken(UUID.randomUUID().toString())
                .name(tUsers.get(0).getName())
                .expiresIn(60*60*1000L)
                .build();
        String token = AuthUtil.setAuthUserInfoVo(userInfoVo);
        return Result.ok(token);
    }
}
