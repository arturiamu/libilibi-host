package com.am.adastra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/8 16:57
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Configuration
@MapperScan("com.am.adastra.mapper")
public class MybatisConfig {
}
