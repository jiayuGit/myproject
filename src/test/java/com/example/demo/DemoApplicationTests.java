package com.example.demo;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.time.Duration;
import java.util.Enumeration;
import java.util.Properties;


class DemoApplicationTests implements ApplicationEventPublisherAware {


    public static void main(String [] args) {
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("META-INF/spring.factories");
            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                UrlResource urlResource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(urlResource);
                System.out.println(properties.getProperty("org.springframework.boot.diagnostics.FailureAnalyzer"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
