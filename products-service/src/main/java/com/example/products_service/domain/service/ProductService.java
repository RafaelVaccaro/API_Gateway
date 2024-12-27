package com.example.products_service.domain.service;

import com.example.products_service.domain.dto.ListarProductDTO;
import com.example.products_service.domain.dto.RegistrarProductDTO;
import com.example.products_service.infrastructure.entity.InsufficientStockException;
import com.example.products_service.infrastructure.entity.Product;
import com.example.products_service.infrastructure.repository.ProductJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela lógica de negócios relacionada aos produtos.
 * Contém métodos para registrar, listar, deletar, validar e atualizar o estoque de produtos.
 */
@Service // Indica que esta classe é um serviço gerenciado pelo Spring
@RequiredArgsConstructor // Gera um construtor para os atributos finais, facilitando a injeção de dependências.
public class ProductService {

    private final ProductJPARepository productJPARepository; // Repositório para operações de persistência de produtos

    /**
     * Registra um novo produto.
     *
     * @param registrarProductDTO DTO contendo os dados do produto a ser registrado
     * @return O produto registrado
     */
    public Product registrar(RegistrarProductDTO registrarProductDTO) {
        // Salva o produto no banco de dados.
        return productJPARepository.save(new Product(registrarProductDTO));
    }

    /**
     * Lista todos os produtos de forma paginada.
     *
     * @param pageable Objeto que contém informações de paginação.
     * @return Uma página contendo os produtos com os dados mapeados para ListarProductDTO
     */
    public Page<ListarProductDTO> listar(Pageable pageable) {
        // Recupera todos os produtos com base na paginação e os mapeia para ListarProductDTO
        return productJPARepository.findAll(pageable).map(ListarProductDTO::new);
    }

    /**
     * Deleta um produto pelo seu ID.
     *
     * @param id ID do produto a ser deletado
     */
    public void deletar(Long id) {
        productJPARepository.deleteById(id);
    }

    /**
     * Valida se um produto existe com base no ID.
     *
     * @param id ID do produto a ser verificado
     * @return true se o produto existir, false caso contrário
     */
    public boolean validarProductPorId(Long id) {
        return productJPARepository.existsById(id);
    }

    /**
     * Retorna o preço de um produto pelo seu ID.
     *
     * @param id ID do produto
     * @return O preço do produto
     */
    public Double getPricePorId(Long id) {
        return productJPARepository.getReferenceById(id).getPrice();
    }

    /**
     * Subtrai a quantidade do estoque de um produto.
     *
     * @param id ID do produto
     * @param quantity Quantidade a ser subtraída do estoque
     * @throws EntityNotFoundException Se o produto não for encontrado
     * @throws IllegalArgumentException Se a quantidade no estoque for insuficiente
     */
    public void subStock(Long id, Integer quantity) {
        // Recupera o produto com o ID fornecido, lança exceção se não encontrado
        Product product = productJPARepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));

        // Verifica se a quantidade disponível é suficiente, lança exceção se não for
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Estoque insuficiente para o produto com ID " + id); // Exceção personalizada para estoque insuficiente.
        }

        // Atualiza o estoque do produto subtraindo a quantidade fornecida
        product.setStock(product.getStock() - quantity);
    }
}
