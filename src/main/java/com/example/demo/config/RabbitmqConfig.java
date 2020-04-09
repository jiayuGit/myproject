package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Configuration
public class RabbitmqConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(((correlationData, ack, messge) ->
                log.info("消息发送成功correlation({}),ack({}),messge({})", correlationData, ack, messge)));

        return rabbitTemplate;
    }

    //延迟队列  消费者消费数据的队列
    private final static String REGISTER_DELAY_QUEUE = "dev.book.regiter.delay.queue";
    //delay letter 死信消息要发去的交换器
    public final static String REGITER_DELAY_EXCHANGE ="dev.book.regiter.delay.exchange";
    //路由到exchange的键
    public final static String DELAY_ROUTING_KEY = "";
    //正常队列
    public final static String REGITER_QUEUE_NAME = "dev.book.regiter.queue";
    public final static String REGITER_EXCHANGE_NAME ="dev.book.regiter.exchange";
    public final static String ROUTING_KEY = "all";
    @Bean
    public Queue delayProcessQueue(){
        Map<String,Object> params = new HashMap<>();
        //死信消息队列把数据转发到正常交换器
        params.put("x-dead-letter-exchange",REGITER_EXCHANGE_NAME);
        //死信消息转发时添加的routing key  路由键
        params.put("x-dead-letter-routing-key",ROUTING_KEY);
        return new Queue(REGISTER_DELAY_QUEUE,true,false,false,params);
    }
    @Bean
    public DirectExchange delayExchange(){
        return new DirectExchange(REGITER_DELAY_EXCHANGE);
    }
    @Bean
    public Binding dxlBiding(){
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(REGITER_EXCHANGE_NAME);
    }
    @Bean
    public Queue queue(){
        return new Queue(REGITER_QUEUE_NAME,true,false,false,null);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTING_KEY);
    }



}
