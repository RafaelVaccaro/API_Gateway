package com.example.orders_service.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductConsumerService {

    private final RestTemplate restTemplate;
    private final String productServiceURL = "http://products-service:8082/product";

    public boolean validarProductPorId(Long id) {
        try {
            String url = productServiceURL + "/" + id;
            restTemplate.getForObject(url, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public Double getProductPrice(Long id) {
        try {
            String url = productServiceURL + "/price/" + id;
            return restTemplate.getForObject(url, Double.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao obter preço do produto");
        }
    }

    public void
}
