package com.example.demo.controller;

import com.example.demo.entity.TUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(path = "/selectUserPage")
    public List<TUser> selectUserPage(Integer startPage,Integer pageSize){
        PageHelper.startPage(startPage,pageSize);
        return null;
    }
}
