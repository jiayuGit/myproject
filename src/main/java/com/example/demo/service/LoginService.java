package com.example.demo.service;

import com.example.demo.dao.TUserMapper;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.TUser;
import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.model.Result;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LoginService {
    @Autowired
    private TUserMapper tUserMapper;
    
    @Resource(name = "redisTemplateSerializable")
    private RedisTemplate<String, Serializable> redisTemplate;

    @Transactional
    public Result addUser(RegisterDto registerDto) {
        String data =(String) redisTemplate.opsForValue().get(registerDto.getEmaill());
        if (Check.NuNStr(data)||!data.equals(registerDto.getAuthCode())){
            return Result.fail("验证码不正确");
        }
        TUser tUser = new TUser();
        tUser.setUuid(UUID.randomUUID().toString());
        tUser.setEmaill(registerDto.getEmaill());
        tUser.setPwd(registerDto.getPwd());
        tUserMapper.insert(tUser);
        return Result.ok("添加成功");

    }


    public List<TUser> selectUserPage() {
        return tUserMapper.selectUserPage();
    }

    public Result login(TUser user) {
        List<TUser> tUsers = tUserMapper.selectByUser(user);
        if (Check.NuNCollection(tUsers)) {
            return Result.fail("账号不存在或密码错误");
        }
        AuthUserInfoVo userInfoVo = AuthUserInfoVo.builder()
                .accessToken(UUID.randomUUID().toString())
                .name(tUsers.get(0).getName())
                .expiresIn(60 * 60 * 1000L)
                .build();
        String token = AuthUtil.setAuthUserInfoVo(userInfoVo);
        return Result.ok(token);
    }
}
