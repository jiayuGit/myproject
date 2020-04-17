package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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
 * @date Created in 2020年04月16日 19:37
 * @since 1.0
 */
@EnableScheduling
@EnableAsync
@Configuration
public class ScheduleThreadPoolConfig {
    @Bean
    @Autowired
    public ThreadPoolTaskScheduler  threadPoolTaskScheduler(TaskSchedulerBuilder builder){
        return builder.build();
    }
    @Bean
    public TaskSchedulerBuilder taskSchedulerBuilder(){
        TaskSchedulerBuilder builder = new TaskSchedulerBuilder();
        builder = builder.poolSize(3);
        builder = builder.threadNamePrefix("myThread-");
        return builder;
    }

}
