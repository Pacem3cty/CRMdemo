package com.example.crmdemo.common.config;

import com.example.crmdemo.common.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 拦截器注册类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    @Autowired
    public void setTokenInterceptor(TokenInterceptor tokenInterceptor){// 注入令牌拦截器
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//重写添加拦截器的方法addInterceptors
        // 添加自定义的令牌拦截器TokenInterceptor 拦截除用户登录请求外的所有请求
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/*/**").excludePathPatterns("/user/login");
    }
}
