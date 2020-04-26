package com.example.demo.service;

import com.example.demo.constant.Constants;
import com.example.demo.model.EmaillDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SendDataService {

    @Resource(name = "redisTemplateSerializable")
    private RedisTemplate<String,Serializable> redisTemplate;


    @Autowired
    private MailService mailService;
    public void sendAuthCode(EmaillDto emaillDto){
        String data = String.valueOf((int) (Math.random()*1000000));
        redisTemplate.opsForValue().set(Constants.REGISTERKEY+emaillDto.getEmaill(),data,5,TimeUnit.MINUTES);
        String sendData = String.format(Constants.MAILLCONTENT,data);
        mailService.sendSimpleMail(emaillDto.getEmaill(),Constants.MAILLTPIC,sendData);
    }
}
