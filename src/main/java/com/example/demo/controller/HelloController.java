package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class HelloController {
    @GetMapping("/login")
    public String login(HttpServletRequest request,String name){
        log.info("访问login{}",request.getRequestedSessionId());
        return "login";
    }
    @GetMapping("/index")
    public String index(HttpServletRequest request,String token){
        log.info("访问index{}",request.getRequestedSessionId()+token);
        request.setAttribute("token",token);
        return "index";
    }
    @GetMapping("/register")
    public String register(HttpServletRequest request,String token){
        log.info("访问register{}",request.getRequestedSessionId()+token);
        request.setAttribute("token",token);
        return "register";
    }
    @GetMapping("/liveRoom")
    public String live(HttpServletRequest request,String token){
        log.info("访问live{}",request.getRequestedSessionId()+token);
        request.setAttribute("token",token);
        return "liveRoom";
    }

}
