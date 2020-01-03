package com.example.demo.interceptor;

import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.basic.BasicInfo;
import com.example.demo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

public class TokenConfirmInterceptor implements HandlerInterceptor {
    @Resource
    RedisUtil redisUtil;

    static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Method method=handlerMethod.getMethod();
            if (method.isAnnotationPresent(UserLoginToken.class)){

                String token=request.getHeader("LOGIN_USER_TOKEN");
                String id=request.getHeader("LOGIN_USER_ID");

                logger.info("LOGIN_USER_ID:{}",id);
                logger.info("LOGIN_USER_TOKEN:{}",token);
                if ("".equals(token) || "".equals(id)){
                    return false;
                } else
                if (redisUtil.count(id,token) ){
                    logger.info("验证成功");
                    return true;
                }else {

                    logger.info("INTERCEPTOR验证失败");

                    response.sendRedirect("/api/v0/NoToken");
                    return  false;
                }
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
