package com.am.libilibi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/10 9:20
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    /**
     * 把我们定义的拦截器类注册为Bean
     */
    @Bean
    public HandlerInterceptor getInterceptor() {
        return new UserInterceptor();
    }

    /**
     * 添加拦截器，并配置拦截地址
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> pathPatterns = new ArrayList<>();
//        pathPatterns.add("/update");
//        registry.addInterceptor(getInterceptor()).addPathPatterns(pathPatterns);
//    }
    /*
     * 只排除登录
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/login");
        registry.addInterceptor(getInterceptor()).excludePathPatterns(pathPatterns);
    }
}
