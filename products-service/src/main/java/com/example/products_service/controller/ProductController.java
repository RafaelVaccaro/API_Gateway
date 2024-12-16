package com.example.products_service.controller;

import com.example.products_service.domain.dto.DetalhamentoProductDTO;
import com.example.products_service.domain.dto.ListarProductDTO;
import com.example.products_service.domain.dto.RegistrarProductDTO;
import com.example.products_service.domain.service.ProductService;
import com.example.products_service.infrastructure.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product registrar(@RequestBody RegistrarProductDTO registrarProductDTO) {
        return productService.registrar(registrarProductDTO);
    }

    @GetMapping
    public Page<ListarProductDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return productService.listar(pageable);
    }

    @GetMapping("/{id}")
    public DetalhamentoProductDTO detalharProduct(@PathVariable Long id) {
        return productService.detalharProduto(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        productService.deletar(id);
    }
}
