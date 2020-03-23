package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public String addUser(User user){
        try {
            userDao.insert(user);
        }catch (Exception e){
            log.info("新增用户失败:{}",e);
            return "添加失败";
        }
        return "添加成功";

    }
}
