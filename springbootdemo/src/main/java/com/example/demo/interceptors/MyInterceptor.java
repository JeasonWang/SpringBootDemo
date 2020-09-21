package com.example.demo.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/21 21:32
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Resource
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InterruptedException, ExecutionException, IOException {
        System.out.println("========== 进入拦截器 ====");
        return limitFlow();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }


    public boolean limitFlow() {
        Long currentTime = new Date().getTime();
        System.out.println(currentTime);
        if (redisTemplate.hasKey("limit")) {
            Integer count = redisTemplate.opsForZSet().rangeByScore("limit", currentTime - 1000, currentTime).size();        // intervalTime是限流的时间
            System.out.println(count);
            if (count != null && count > 1000) {
                log.info("每分钟最多只能访问1000次");
                return false;
            }
        }
        redisTemplate.opsForZSet().add("limit", String.valueOf(currentTime), currentTime);
        return true;
    }


}
