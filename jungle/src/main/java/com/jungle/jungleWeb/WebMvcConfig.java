package com.jungle.jungleweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.cors.allowed-origin:http://localhost:3000}")
    private String allowedOrigin;
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //로컬
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin)
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
    }
}
