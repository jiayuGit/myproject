package com.example.demo.intercepor;

import com.example.demo.model.AuthUserInfoVo;
import com.example.demo.util.AuthUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHanderIntercepor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("access-token");

        try {
            if (token == null || token.equals("")) {
                response.sendRedirect("/login");
                return false;
            }
            AuthUserInfoVo authUserInfoVo = AuthUtil.getAuthUserInfoVo(token);
            if (authUserInfoVo==null){
                response.sendRedirect("/login");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
