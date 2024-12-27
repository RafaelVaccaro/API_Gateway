package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Classe principal da aplicação de API Gateway.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // Autoconfigura o Spring Boot e desabilita manualmente qualquer configuração com banco de dados.
public class ApiGatewayApplication {
	public static void main(String[] args) {
		// Chama o SpringApplication.run para iniciar o contexto Spring e a aplicação.
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
