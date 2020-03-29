package com.example.demo.controller;

import com.example.demo.model.EmaillDto;
import com.example.demo.model.Result;
import com.example.demo.service.MailService;
import com.example.demo.service.SendDataService;
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
    private SendDataService sendDataService;
    @PostMapping(value = "/authCode",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Result sendAuthCode(@RequestBody EmaillDto emaillDto){
        try {
            sendDataService.sendAuthCode(emaillDto);
        }catch (Exception e){
            log.error("e={}",e);
            return Result.fail("验证码发送失败");
        }
        return Result.ok(emaillDto.getEmaill());
    }
}
