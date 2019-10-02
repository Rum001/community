package com.itrum.community.community.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper createPageHelper(){
        PageHelper pageHelper=new PageHelper();
        return pageHelper;
    }
}
