package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.dto.RegisterDto;
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

import javax.annotation.Resource;
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
    @Resource(name = "redisTemplateSerializable")
    private RedisTemplate<String, Serializable> redisTemplate;

    @PostMapping(path = "/logoing",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result logoing(@RequestBody TUser user) {

        if (Check.NuNObj(user) || Check.NuNStr(user.getName()) || Check.NuNStr(user.getPwd())) {
            return Result.fail("账号密码不能为空");
        }

        return Result.ok(loginService.login(user));
    }

@PostMapping(path = "/registerUser",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Result registerUser(@RequestBody RegisterDto registerDto) {
        try {
            return loginService.addUser(registerDto);
        } catch (Exception e) {
            log.error("用户注册失败入参{} e={}", registerDto, e);
            return Result.fail("注册失败");
        }
    }
    @PostMapping(path = "/selectUserPage",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

      public Result selectUserPage(@RequestBody BasicPageDto pageDto) {
        PageHelper.startPage(pageDto.getStartPage(), pageDto.getPageSize());
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(s, pageDto, 1000, TimeUnit.SECONDS);
        BasicPageDto s1 = (BasicPageDto) redisTemplate.opsForValue().get(s);
        log.info(s1 + " " + s1);
        return Result.ok(loginService.selectUserPage());
    }

}
