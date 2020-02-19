package com.nickless.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * creat by nickless
 *
 * @Date 2020/2/19 10:31
 */
@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor( sessionInterceptor).addPathPatterns("/**");

    }
}
