package com.itrum.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc   //如果使用了springboot的自动装备  会导致所有的静态资源也将被拦截 所以 EnableWebMvc 注解不能加
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptor requestInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(requestInterceptor);
        //配置拦截的路径
        ir.addPathPatterns("/**");
        //配置不被拦截的路径
        ir.excludePathPatterns("/","/static/**","/callback","/question/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
