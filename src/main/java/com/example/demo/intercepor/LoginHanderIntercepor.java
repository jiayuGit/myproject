package com.example.demo.intercepor;

import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.util.AuthUtil;
import com.example.demo.util.Check;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginHanderIntercepor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("access-token");
        if (Check.NuNStr(token)&&!Check.NuNObj(request.getParameterMap().get("access-token"))){
            token =(String) request.getParameterMap().get("access-token")[0];
        }

        log.info("请求地址path={}",request.getServletPath());
        try {
            if (Check.NuNStr(token)) {
                response.sendRedirect("/login");
                return false;
            }
            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo(token);
            if (Check.NuNObj(authUserInfoVo)){
                response.sendRedirect("/login");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
