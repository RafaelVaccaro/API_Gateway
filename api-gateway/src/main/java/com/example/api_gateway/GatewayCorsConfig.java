package com.example.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayCorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:4200"); // Domínio do frontend
        corsConfig.addAllowedHeader("*"); // Permite todos os headers
        corsConfig.addAllowedMethod("*"); // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
        corsConfig.setAllowCredentials(true); // Permite envio de cookies/autenticação

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Aplica o CORS a todas as rotas

        return new CorsWebFilter(source);
    }
}
