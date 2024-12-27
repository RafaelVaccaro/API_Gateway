package com.example.orders_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação de serviço de pedidos.
 */
@SpringBootApplication // Anotação que indica que esta é uma classe de configuração do Spring Boot, habilitando o auto-configuração.
public class OrdersServiceApplication {

	public static void main(String[] args) {
		// Chama o SpringApplication.run para iniciar o contexto Spring e a aplicação.
		SpringApplication.run(OrdersServiceApplication.class, args);
	}

}
