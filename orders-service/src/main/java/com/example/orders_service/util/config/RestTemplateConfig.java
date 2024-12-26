package com.example.orders_service.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuração para o RestTemplate.
 * Cria um bean do tipo RestTemplate para ser utilizado em toda a aplicação.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Cria um bean do RestTemplate.
     *
     * @return uma instância de RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
