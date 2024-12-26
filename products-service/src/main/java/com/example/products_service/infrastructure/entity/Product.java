package com.example.products_service.infrastructure.entity;

import com.example.products_service.domain.dto.RegistrarProductDTO;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um produto no sistema, com informações sobre nome, descrição, preço e estoque.
 * Esta classe está mapeada para a tabela 'products' no banco de dados.
 */
@Table(name = "products") // Define a tabela no banco de dados que esta entidade irá mapear
@Entity(name = "Product") // Define o nome da entidade para o Hibernate
@Getter // Gera automaticamente os métodos de getter para os campos
@Setter // Gera automaticamente os métodos de setter para os campos
@NoArgsConstructor // Gera um construtor sem parâmetros
@AllArgsConstructor // Gera um construtor com todos os parâmetros
@EqualsAndHashCode(of = "id") // Gera os métodos equals e hashCode baseados no campo 'id'
public class Product {

    /**
     * Identificador único do produto.
     * Este campo é a chave primária da tabela 'products' e é gerado automaticamente.
     */
    @Id // Marca este campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a geração automática do ID
    private Long id;

    /**
     * Nome do produto.
     * Este campo não pode ser nulo, conforme a configuração da tabela no banco de dados.
     */
    @Column(name = "name", nullable = false) // Define a coluna 'name' na tabela
    private String name;

    /**
     * Descrição do produto.
     * Este campo não pode ser nulo, conforme a configuração da tabela no banco de dados.
     */
    @Column(name = "description", nullable = false) // Define a coluna 'description' na tabela
    private String description;

    /**
     * Preço do produto.
     * Este campo não pode ser nulo, conforme a configuração da tabela no banco de dados.
     */
    @Column(name = "price", nullable = false) // Define a coluna 'price' na tabela
    private Double price;

    /**
     * Quantidade em estoque do produto.
     * Este campo não pode ser nulo, conforme a configuração da tabela no banco de dados.
     */
    @Column(name = "stock", nullable = false) // Define a coluna 'stock' na tabela
    private Integer stock;

    /**
     * Construtor que cria um produto a partir dos dados fornecidos no DTO.
     *
     * @param registrarProductDTO DTO com os dados necessários para criar o produto
     */
    public Product(RegistrarProductDTO registrarProductDTO) {
        this.name = registrarProductDTO.name(); // Atribui o nome do produto
        this.description = registrarProductDTO.description(); // Atribui a descrição do produto
        this.price = registrarProductDTO.price(); // Atribui o preço do produto
        // Atribui o estoque do produto, caso fornecido, ou 0 se não informado
        if (registrarProductDTO.stock() != null)
            this.stock = registrarProductDTO.stock();
        else
            this.stock = 0;
    }
}

