package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.entity.TUser;
import com.example.demo.model.Result;
import com.example.demo.service.LoginService;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @PostMapping("/logoing")
    public Result addUser(@RequestBody   TUser user){

        if (Check.NuNObj(user)|| Check.NuNStr(user.getName())||Check.NuNStr(user.getPwd())){
            return Result.fail("账号密码不能为空");
        }

        return Result.ok(loginService.login(user));
    }
    @PostMapping(path = "/selectUserPage",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Result selectUserPage(@RequestBody BasicPageDto pageDto){
        PageHelper.startPage(pageDto.getStartPage(),pageDto.getPageSize());
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(s,pageDto,1000,TimeUnit.SECONDS);
        BasicPageDto s1 = (BasicPageDto) redisTemplate.opsForValue().get(s);
        log.info(s1+" "+s1);
        return Result.ok(loginService.selectUserPage());
    }

}
