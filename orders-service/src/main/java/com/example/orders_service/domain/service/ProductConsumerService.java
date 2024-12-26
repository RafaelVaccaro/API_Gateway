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

    /**
     * Valida se o produto existe no serviço de produtos.
     *
     * @param id O ID do produto a ser verificado.
     * @return true se o produto for encontrado, false caso contrário.
     */
    public boolean validarProductPorId(Long id) {
        try {
            String url = productServiceURL + "/" + id;
            restTemplate.getForObject(url, Void.class); // Realiza a requisição GET
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // Produto não encontrado
        }
    }

    /**
     * Obtém o preço de um produto pelo ID.
     *
     * @param id O ID do produto.
     * @return O preço do produto ou null se não encontrado.
     */
    public Double getProductPrice(Long id) {
        try {
            String url = productServiceURL + "/price/" + id;
            return restTemplate.getForObject(url, Double.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null; // Produto não encontrado
        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao obter preço do produto"); // Exceção para erro de requisição
        }
    }

    /**
     * Atualiza o estoque de um produto pelo ID.
     *
     * @param id       O ID do produto.
     * @param quantity A quantidade a ser subtraída do estoque.
     */
    public void subStock(Long id, Integer quantity) {
        String url = productServiceURL + "/stock/" + id;
        try {
            restTemplate.put(url, quantity); // Realiza a requisição PUT
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Produto com ID " + id + " não encontrado"); // Produto não encontrado
        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao atualizar estoque do produto com ID " + id); // Erro ao realizar a requisição
        }
    }
}
