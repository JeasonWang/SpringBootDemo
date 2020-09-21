package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 14:56
 */
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisConfig {
}
