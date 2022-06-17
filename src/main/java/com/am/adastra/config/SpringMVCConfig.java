package com.am.adastra.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/30 10:32
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@SuppressWarnings("deprecation")
@SpringBootConfiguration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Resource
    private FilterConfig filterConfig;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filterConfig).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
