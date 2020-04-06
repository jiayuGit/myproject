package com.example.demo.listener;

import com.example.demo.config.RabbitmqConfig;
import com.example.demo.model.Book;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MessageHandler {
    @RabbitListener(queues = {RabbitmqConfig.REGITER_QUEUE_NAME})
    public void bookMassgeListener(Book book, Message message, Channel channel){
        log.info("消费时间是={} book={},message={}",LocalDateTime.now(),book,message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);//是否批量回复小于该tag的所有ack
        } catch (IOException e) {
            log.error("回复ack失败obj={} message={},e={}",book,message,channel);
        }
        log.info("回复ack完成");
    }
}
