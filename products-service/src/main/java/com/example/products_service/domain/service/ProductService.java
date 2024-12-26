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

/**
 * Serviço responsável pela lógica de negócios relacionada aos produtos.
 * Contém métodos para registrar, listar, deletar e atualizar produtos.
 */
@Service // Indica que esta classe é um serviço gerenciado pelo Spring
@RequiredArgsConstructor // Gera um construtor com todos os parâmetros finais (injetados via DI)
public class ProductService {

    private final ProductJPARepository productJPARepository; // Repositório para operações de persistência de produtos

    /**
     * Registra um novo produto no sistema.
     *
     * @param registrarProductDTO DTO contendo os dados do produto a ser registrado
     * @return O produto registrado
     */
    public Product registrar(RegistrarProductDTO registrarProductDTO) {
        // Cria um novo produto a partir do DTO e salva no repositório
        return productJPARepository.save(new Product(registrarProductDTO));
    }

    /**
     * Lista todos os produtos de forma paginada.
     *
     * @param pageable Objeto de paginação com informações sobre a página e o tamanho
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
        // Deleta o produto com base no ID fornecido
        productJPARepository.deleteById(id);
    }

    /**
     * Valida se um produto existe com base no ID.
     *
     * @param id ID do produto a ser verificado
     * @return true se o produto existir, false caso contrário
     */
    public boolean validarProductPorId(Long id) {
        // Verifica se o produto com o ID fornecido existe
        return productJPARepository.existsById(id);
    }

    /**
     * Retorna o preço de um produto pelo seu ID.
     *
     * @param id ID do produto
     * @return O preço do produto
     */
    public Double getPricePorId(Long id) {
        // Retorna o preço do produto com o ID fornecido
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
            throw new IllegalArgumentException("Estoque insuficiente para o produto com ID " + id);
        }

        // Atualiza o estoque do produto subtraindo a quantidade fornecida
        product.setStock(product.getStock() - quantity);
    }

}
