package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.time.Duration;


class DemoApplicationTests {

    @Test
    void contextLoads() {
        ThreadLocal threadLocal = new ThreadLocal();
//        threadLocal.set(new byte[1024*1024*100]);
//        SoftReference softReference = new SoftReference(new Object());
//        WeakReference weakReference = new WeakReference(new Object());
        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(v->{

        });
        Flux.range(1,100).reduce((v1,v2)->{
            System.out.println(Thread.currentThread()+" "+v1+v2);
            return v1+v2;
        }).subscribe(System.out::println);

        Flux.range(1,1000).take(2).subscribe(System.out::println);
        Flux.just(5,10).flatMap(x->Flux.interval(Duration.ofSeconds(x*10,100)).take(x)).toStream().forEach(System.out::println);
    }

}
