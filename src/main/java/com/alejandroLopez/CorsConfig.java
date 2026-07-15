package com.alejandroLopez;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8100"); // Permitir el dominio del frontend
        config.addAllowedMethod("*"); // Permitir todos los métodos
        config.addAllowedHeader("*"); // Permitir todos los encabezados

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Aplicar CORS a todas las rutas

        return new CorsFilter(source);
    }
}
