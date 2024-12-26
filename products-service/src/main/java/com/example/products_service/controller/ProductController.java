package com.example.products_service.controller;

import com.example.products_service.domain.dto.ListarProductDTO;
import com.example.products_service.domain.dto.RegistrarProductDTO;
import com.example.products_service.domain.service.ProductService;
import com.example.products_service.infrastructure.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelas operações de produtos.
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Endpoint para registrar um novo produto.
     *
     * @param registrarProductDTO Dados do produto a ser registrado.
     * @return O produto recém-criado.
     */
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Product registrar(@RequestBody RegistrarProductDTO registrarProductDTO) {
        return productService.registrar(registrarProductDTO);
    }

    /**
     * Endpoint para listar produtos com paginação.
     *
     * @param pageable Configurações de paginação.
     * @return Uma página de produtos.
     */
    @GetMapping
    public Page<ListarProductDTO> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return productService.listar(pageable);
    }

    /**
     * Endpoint para deletar um produto.
     *
     * @param id ID do produto a ser deletado.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        productService.deletar(id);
    }

    /**
     * Endpoint para validar a existência de um produto pelo ID.
     *
     * @param id ID do produto a ser validado.
     * @return true se o produto existir, false caso contrário.
     */
    @GetMapping("/{id}")
    public boolean validarProductPorId(@PathVariable Long id) {
        return productService.validarProductPorId(id);
    }

    /**
     * Endpoint para obter o preço de um produto pelo ID.
     *
     * @param id ID do produto.
     * @return O preço do produto.
     */
    @GetMapping("/price/{id}")
    public Double getPricePorId(@PathVariable Long id) {
        return productService.getPricePorId(id);
    }

    /**
     * Endpoint para subtrair a quantidade de estoque de um produto.
     *
     * @param id ID do produto.
     * @param quantity Quantidade a ser subtraída do estoque.
     */
    @PutMapping("/stock/{id}")
    @Transactional
    public void subStock(@PathVariable Long id, @RequestBody Integer quantity) {
        productService.subStock(id, quantity);
    }
}


