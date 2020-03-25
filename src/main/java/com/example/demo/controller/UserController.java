package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.entity.TUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @PostMapping("/add")
    public String addUser(@RequestBody TUser user){
        user.setUuid(UUID.randomUUID().toString());
        return userService.addUser(user);
    }
    @PostMapping(path = "/selectUserPage",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TUser> selectUserPage(@RequestBody BasicPageDto pageDto){
        PageHelper.startPage(pageDto.getStartPage(),pageDto.getPageSize());
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(s,pageDto,1000,TimeUnit.SECONDS);
        BasicPageDto s1 = (BasicPageDto) redisTemplate.opsForValue().get(s);
        log.info(s1+" "+s1);
        return userService.selectUserPage();
    }
}
