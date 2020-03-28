package com.example.demo.controller;

import com.example.demo.model.Result;
import com.example.demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.tools.JavaCompiler;

@Slf4j
@RestController
@RequestMapping("/send")
public class SendDataController {


    @PostMapping(value = "/authCode",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result sendAuthCode(@RequestBody String emaill){
        return Result.ok(emaill);
    }
}
