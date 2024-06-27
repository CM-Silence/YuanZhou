package com.example.config;

import com.example.interceptor.JWTInterceptors;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptors())
                .addPathPatterns("/user/test")  //  其他接口token验证
                .excludePathPatterns("/user/login");  //所有用户都放行
    }
}
