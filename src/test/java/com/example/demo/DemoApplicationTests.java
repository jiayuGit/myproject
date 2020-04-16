package com.example.demo;

//import org.junit.jupiter.api.Test;
import com.example.demo.entity.TUser;
import com.example.demo.util.Check;
import org.junit.Test;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.time.Duration;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;

@Component
class DemoApplicationTests implements ApplicationEventPublisherAware {


    public static void main(String [] args) {
        for (String s:"".split("#")
             ) {
            System.out.println(s+"="+s.length());
        }
        System.out.println("".split("#"));
        Optional<TUser> optional = Optional.empty();
        Optional<TUser> user = Optional.ofNullable(new TUser());
        Object o = Optional.ofNullable(null).orElse(new TUser());
        TUser tUser = null;
        System.out.println(o);
        Optional.ofNullable(null).ifPresent(v-> System.out.println(v));
        TUser tUser1 = Optional.ofNullable(tUser).filter(v -> !Check.NuNStr(v.getEmaill())).orElse(TUser.builder().emaill("aaaa").build());

        System.out.println(tUser1);
    }
@Scheduled(cron = "")
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
