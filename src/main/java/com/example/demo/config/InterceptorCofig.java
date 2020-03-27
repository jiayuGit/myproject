package com.example.demo.config;

import com.example.demo.intercepor.LoginHanderIntercepor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorCofig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPathPatterns = {
                "/**"
        };
        //不需要的拦截路径，同上
        String[] excludePathPatterns = {
                "/login","/static/**"
        };
        registry.addInterceptor(new LoginHanderIntercepor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);


    }
}
