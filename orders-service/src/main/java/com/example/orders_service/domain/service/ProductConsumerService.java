package com.example.orders_service.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductConsumerService {

    private final RestTemplate restTemplate;
    private final String productServiceURL = "http://products-service:8082/product";

    public boolean validarProductPorId(Long id) {
        try {
            String url = productServiceURL + "/" + id;
            restTemplate.getForObject(url, Void.class); // Apenas verifica se o produto existe
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
