package com.example.products_service.domain.service;

import com.example.products_service.domain.dto.DetalhamentoProductDTO;
import com.example.products_service.domain.dto.ListarProductDTO;
import com.example.products_service.domain.dto.RegistrarProductDTO;
import com.example.products_service.infrastructure.entity.Product;
import com.example.products_service.infrastructure.repository.ProductJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJPARepository productJPARepository;

    public Product registrar(RegistrarProductDTO registrarProductDTO) {
        return productJPARepository.save(new Product(registrarProductDTO));
    }

    public Page<ListarProductDTO> listar(Pageable pageable) {
        return productJPARepository.findAll(pageable).map(ListarProductDTO::new);
    }

    public void deletar(Long id) {
        productJPARepository.deleteById(id);
     }

    public boolean validarProductPorId(Long id) {
        return productJPARepository.existsById(id);
    }

    public Double getPricePorId(Long id) {
        return productJPARepository.getReferenceById(id).getPrice();
    }

    public void subStock(Long id, Integer quantity) {
        Product product = productJPARepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto com ID " + id);
        }

        product.setStock(product.getStock() - quantity);
    }

}
