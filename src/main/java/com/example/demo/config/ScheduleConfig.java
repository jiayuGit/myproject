package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author dengjy
 * @version 1.0
 * @date Created in 2020年04月16日 10:17
 * @since 1.0
 */
@Async
@Component
@Slf4j
public class ScheduleConfig {
    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

//    @Autowired
//    Executor executor;
//    @Scheduled(cron = "0/1 * * * * *")
    public void schedule(){
      log.info("时间={} Thread={} size={}", LocalDateTime.now(),Thread.currentThread().getName(),taskScheduler.getPoolSize());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();

    }
}
