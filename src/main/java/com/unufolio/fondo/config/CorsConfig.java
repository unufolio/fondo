package com.unufolio.fondo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
//@Configuration
@Order(-1)
public class CorsConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 添加映射路径
//        registry.addMapping("/**")
//                // 允许哪些原始域
//                .allowedOrigins("*")
//                // 是否发送 Cookie 信息
//                .allowCredentials(true)
//                // 允许哪些请求方法
//                .allowedMethods("*")
//                // 允许哪些头信息
//                .allowedHeaders("*");
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                // 添加映射路径
//                registry.addMapping("/**")
//                        // 允许哪些原始域
//                        .allowedOrigins("*")
//                        // 是否发送 Cookie 信息
//                        .allowCredentials(true)
//                        // 允许哪些请求方法
//                        .allowedMethods("*")
//                        // 允许哪些头信息
//                        .allowedHeaders("*");
//            }
//        };
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
