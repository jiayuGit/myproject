package com.example.demo.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.example.demo.config.RabbitmqConfig;
import com.example.demo.model.Book;
import com.example.demo.model.EmaillDto;
import com.example.demo.model.Result;
import com.example.demo.service.MailService;
import com.example.demo.service.SendDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.print.attribute.standard.Media;
import javax.tools.JavaCompiler;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/send")
public class SendDataController {

    @Autowired
    private SendDataService sendDataService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
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
    @GetMapping(path = "rabbitmqData")
    public Result sendRabbitMq(String data){
        Book boot = Book.builder().id(UUID.randomUUID().toString()).name(data).build();
        rabbitTemplate.convertAndSend(RabbitmqConfig.REGITER_DELAY_EXCHANGE,RabbitmqConfig.DELAY_ROUTING_KEY,boot,
                message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,Book.class.getName());
            message.getMessageProperties().setExpiration(5*1000+"");
               return message; });
        log.info("发送消息时间是={}",LocalDateTime.now());
        return Result.ok();
    }
}
