package com.studyxp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica a permissão para todos os endpoints que começam com /api
                .allowedOrigins("*") // Permite requisições de qualquer origem (ótimo para desenvolvimento)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos HTTP
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}