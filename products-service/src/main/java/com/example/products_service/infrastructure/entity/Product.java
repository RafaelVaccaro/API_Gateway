package com.example.products_service.infrastructure.entity;

import com.example.products_service.domain.dto.RegistrarProductDTO;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um produto no banco de dados.
 * Mapeada com a tabela "products".
 */
@Table(name = "products") // Define o nome da tabela no banco de dados.
@Entity(name = "Product") // Define o nome da entidade para o Hibernate
@Data // Gera getters, setters, toString, equals, hashCode e construtor padrão automaticamente.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode baseados no campo "id".
public class Product {

    @Id // Marca o campo id como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a geração automática do ID
    private Long id;

    @Column(name = "name", nullable = false) // Define a coluna 'name' que não pode ser nula.
    private String name;

    @Column(name = "description", nullable = false) // Define a coluna 'description' que não pode ser nula.
    private String description;

    @Column(name = "price", nullable = false) // Define a coluna 'price' que não pode ser nula.
    private Double price;

    @Column(name = "stock", nullable = false) // Define a coluna 'stock' que não pode ser nula.
    private Integer stock;

    /**
     * Construtor que inicializa um produto com os dados do DTO.
     * @param dados Dados para criar um novo produto (RegistroProductDTO).
     */
    public Product(RegistrarProductDTO dados) {
        this.name = dados.name();
        this.description = dados.description();
        this.price = dados.price();
        this.stock = dados.stock();

    }
}

