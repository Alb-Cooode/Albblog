package com.albert.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // 判断session中有没有用户，如果没有则重定向回登录界面
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/blog/admin");
            return false;
        }
        return true;
    }

}
