package com.albert.blog.config;

import com.albert.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截后端所有的请求
                .addPathPatterns("/admin/**")
                // 放行登录请求
                .excludePathPatterns("/admin","/admin/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取文件的真实路径， work_project代表项目工程名，需更改
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:" + path);
        }else { // linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:" + path);
        }
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
