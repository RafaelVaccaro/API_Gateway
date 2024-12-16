package com.example.products_service.infrastructure.entity;

import com.example.products_service.domain.dto.RegistrarProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    public Product(RegistrarProductDTO registrarProductDTO) {
        this.name = registrarProductDTO.name();
        this.description = registrarProductDTO.description();
        this.price = registrarProductDTO.price();
        if (registrarProductDTO.stock() != null)
            this.stock = registrarProductDTO.stock();
        else
            this.stock = 0;
    }
}
