package com.example.orders_service.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserConsumerService {

    private final RestTemplate restTemplate;
    private final String userServiceURL = "http://users-service:8081/user";

    public boolean validarUserPorId(Long id) {
        try {
            String url = userServiceURL + "/" + id;
            restTemplate.getForObject(url, Void.class); // Apenas verifica se o usuário existe
            return true;
        } catch (Exception e) {
            return false; // Usuário não encontrado
        }
    }

}
