package com.example.demo.controller;

import com.example.demo.model.EmaillDto;
import com.example.demo.model.Result;
import com.example.demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.tools.JavaCompiler;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/send")
public class SendDataController {

    @Autowired
    private MailService mailService;
    @PostMapping(value = "/authCode")
    public Result sendAuthCode(@RequestBody EmaillDto emaillDto){
        try {
            mailService.sendSimpleMail(emaillDto.getEmaill(),"验证码","内容");

        }catch (Exception e){
            log.error("e={}",e);
        }
        return Result.ok(emaillDto.getEmaill());
    }
}
