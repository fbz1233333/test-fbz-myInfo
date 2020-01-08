package com.example.demo.interceptor;

import com.example.demo.annotiation.UserIsAuthor;
import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.dao.NoteMapper;
import com.example.demo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class UserIsAuthorInterceptor implements HandlerInterceptor {

    static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);



    @Value("${headerInfo.loginUserId.name}")
    String loginUserIdtag;

    @Value("${headerInfo.updateNoteId.name}")
    String noteIdTag;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Method method=handlerMethod.getMethod();

            if (method.isAnnotationPresent(UserIsAuthor.class)){

                String userId=request.getHeader(loginUserIdtag);
                String noteId=request.getHeader(noteIdTag);
                logger.info("loginUserId:{}",userId);
                logger.info("updateNoteId:{}",noteId);
                if (userId==null || noteIdTag==null || "".equals(userId)|| "".equals(noteId)
                    || noteMapper.findByIdAndCreateBy(noteId,userId)==null
                ){
                    response.sendRedirect("/api/v0/userIsNotAuthor");
                    return false;
                }else {
                    return true;
                }
//                if ("".equals(token) || "".equals(id) || !redisUtil.count(id,token)){
//                    response.sendRedirect("/api/v0/NoToken");
//                    return false;
//                } else {
//                    return true;
//                }
            }
        }
        return true;

    }

    @Autowired
    NoteMapper noteMapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
