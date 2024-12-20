package com.example.products_service.controller;

import com.example.products_service.domain.dto.DetalhamentoProductDTO;
import com.example.products_service.domain.dto.ListarProductDTO;
import com.example.products_service.domain.dto.RegistrarProductDTO;
import com.example.products_service.domain.service.ProductService;
import com.example.products_service.infrastructure.entity.Product;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Product registrar(@RequestBody RegistrarProductDTO registrarProductDTO) {
        return productService.registrar(registrarProductDTO);
    }

    @GetMapping
    public Page<ListarProductDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return productService.listar(pageable);
    }

    @GetMapping("/detalhamento/{id}")
    public DetalhamentoProductDTO detalharProduct(@PathVariable Long id) {
        return productService.detalharProduto(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        productService.deletar(id);
    }

    @GetMapping("/{id}")
    public boolean validarProductPorId(@PathVariable Long id) {
        return productService.validarProductPorId(id);
    }

    @GetMapping("/price/{id}")
    public Double getPricePorId(@PathVariable Long id) {
        return productService.getPricePorId(id);
    }

    @PutMapping("/stock/{id}")
    @Transactional
    public void subStock(@PathVariable Long id, @RequestBody Integer quantity) {
        productService.subStock(id, quantity);
    }
}

//RestTemplate
