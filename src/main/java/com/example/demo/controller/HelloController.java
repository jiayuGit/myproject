package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class HelloController {
    @GetMapping("/index")
    public String hello(HttpServletRequest request,String name){
        log.info("访问",request.getRequestedSessionId());
        return "index";
    }
    @GetMapping("/login")
    public String login(HttpServletRequest request,String name){
        log.info("访问",request.getRequestedSessionId());
        return "login";
    }
}
