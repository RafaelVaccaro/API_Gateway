package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// A anotação @SpringBootApplication configura a aplicação para ser uma aplicação Spring Boot.
// O atributo 'exclude' é utilizado para excluir a configuração automática do DataSource,
// ou seja, o Spring Boot não tentará configurar um banco de dados, o que é útil para o API Gateway.
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
