package com.example.demo.util;

import com.example.demo.constant.Constants;
import com.example.demo.model.AuthUserInfoVo;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthUtil {
    private static long DELTIMEMAX = 20 * 60 * 1000;

    public static AuthUserInfoVo getAuthUserInfoVo(String token) {
        RedisTemplate<String, Serializable> redisTemplate = ApplicationContextUtil.getBeanByType(RedisTemplate.class);
        AuthUserInfoVo authUserInfoVo = (AuthUserInfoVo) redisTemplate.opsForValue().get(Constants.TOKENKEY + token);
        if (authUserInfoVo != null) {
            Long expiresIn = authUserInfoVo.getExpiresIn() - System.currentTimeMillis();
            if (expiresIn < 0) {
                return null;
            }

            redisTemplate.opsForValue().set(Constants.TOKENKEY + token, authUserInfoVo, expiresIn > DELTIMEMAX ? DELTIMEMAX : expiresIn, TimeUnit.MILLISECONDS);
            return authUserInfoVo;
        }
        return null;
    }

    public static String setAuthUserInfoVo(AuthUserInfoVo authUserInfoVo) {
        RedisTemplate<String, Serializable> redisTemplate = ApplicationContextUtil.getBeanByType(RedisTemplate.class);
        authUserInfoVo.setAccessToken(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(Constants.TOKENKEY + authUserInfoVo.getAccessToken(), authUserInfoVo, DELTIMEMAX, TimeUnit.MILLISECONDS);
        return authUserInfoVo.getAccessToken();
    }

    public static Boolean outAuthUserInfoVo(String token){
        RedisTemplate<String, Serializable> redisTemplate = ApplicationContextUtil.getBeanByType(RedisTemplate.class);
        return redisTemplate.delete(Constants.TOKENKEY+token);
    }
}
