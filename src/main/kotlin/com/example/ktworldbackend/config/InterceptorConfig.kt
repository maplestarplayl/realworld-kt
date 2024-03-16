package com.example.ktworldbackend.config

import com.example.ktworldbackend.interceptor.HttpInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig  (val interceptor: HttpInterceptor): WebMvcConfigurer{
    override fun addInterceptors(registry: InterceptorRegistry){
        registry.addInterceptor(interceptor).addPathPatterns("/**")
            .excludePathPatterns("/users/**")
            .excludePathPatterns("/articles/{articleId}")
    }
}