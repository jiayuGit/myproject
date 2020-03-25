package com.example.demo.controller;

import com.example.demo.dto.BasicPageDto;
import com.example.demo.entity.TUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public String addUser(@RequestBody TUser user){
        user.setUuid(UUID.randomUUID().toString());
        return userService.addUser(user);
    }
    @PostMapping(path = "/selectUserPage",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TUser> selectUserPage(@RequestBody BasicPageDto pageDto){
        PageHelper.startPage(pageDto.getStartPage(),pageDto.getPageSize());
        return userService.selectUserPage();
    }
}
