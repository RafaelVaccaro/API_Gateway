package com.example.orders_service.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserConsumerService {

    private final RestTemplate restTemplate;
    private final String userServiceURL = "http://users-service:8081/user";

    /**
     * Verifica se o usuário existe no serviço de usuários.
     *
     * @param id O ID do usuário a ser verificado.
     * @return true se o usuário for encontrado, false caso contrário.
     */
    public boolean validarUserPorId(Long id) {
        try {
            String url = userServiceURL + "/" + id;
            restTemplate.getForObject(url, Void.class); // Realiza a requisição GET
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // Caso não encontre o usuário
        } catch (RestClientException e) {
            return false; // Outros erros de requisição
        }
    }
}
