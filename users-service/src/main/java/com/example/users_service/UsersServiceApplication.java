package com.example.users_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação de serviço de usuários.
 * A anotação @SpringBootApplication marca esta classe como a configuração principal do Spring Boot.
 * A aplicação será iniciada a partir do método main.
 */
@SpringBootApplication // Anotação que indica que esta é uma classe de configuração do Spring Boot, habilitando o auto-configuração e a varredura de componentes.
public class UsersServiceApplication {

	//Método principal que inicia a aplicação Spring Boot.
	public static void main(String[] args) {
		// Chama o SpringApplication.run para iniciar o contexto Spring e a aplicação.
		SpringApplication.run(UsersServiceApplication.class, args);
	}

}
