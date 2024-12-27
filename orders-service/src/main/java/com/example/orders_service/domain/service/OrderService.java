package com.example.orders_service.domain.service;

import com.example.orders_service.domain.dto.*;
import com.example.orders_service.infrastructure.entity.*;
import com.example.orders_service.infrastructure.repository.OrderItemJPARepository;
import com.example.orders_service.infrastructure.repository.OrderJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada aos pedidos.
 * Contém métodos para registrar, listar, deletar.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJPARepository orderJPARepository; // Repositório para operações de persistência de pedidos.
    private final OrderItemJPARepository orderItemJPARepository; // Repositório para operações de persistência de itens de pedidos.
    private final ProductConsumerService productConsumerService; // Serviço que consome requisições do serviço de produtos.
    private final UserConsumerService userConsumerService; // Serviço que consome requisições do serviço de usuários.

    /**
     * Registra um pedido, validando usuário e produtos.
     * Lança exceções caso o usuário ou produto não existam ou se houver estoque insuficiente.
     *
     * @param registroOrderDTO Dados para registrar o pedido.
     * @return O pedido registrado.
     * @throws UserNotFoundException Se o usuário com o ID fornecido não for encontrado.
     * @throws ProductNotFoundException Se algum produto com o ID fornecido não for encontrado.
     */
    public Order registrarOrder(RegistroOrderDTO registroOrderDTO) {

        // Valída a existencia do usuario.
        if (!userConsumerService.validarUserPorId(registroOrderDTO.userId())) {
            throw new UserNotFoundException("User com ID: " + registroOrderDTO.userId() + " não existe");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        // Processa cada item do pedido
        for (RegistroOrderItemDTO itemDTO : registroOrderDTO.orderItems()) {

            if (!productConsumerService.validarProductPorId(itemDTO.productId())) {
                throw new ProductNotFoundException("Product com ID: " + itemDTO.productId() + " não existe");
            }

            // Atualiza o estoque e calcula o preço total
            productConsumerService.subStock(itemDTO.productId(), itemDTO.quantity());
            double itemPrice = productConsumerService.getProductPrice(itemDTO.productId()) * itemDTO.quantity();
            totalPrice += itemPrice;

            // Cria o item de pedido
            OrderItem orderItem = new OrderItem(itemDTO);
            orderItems.add(orderItem);
        }

        // Cria o pedido com os itens e o preço total
        Order order = new Order(registroOrderDTO.userId(), totalPrice, orderItems);

        // Salva o pedido
        Order savedOrder = orderJPARepository.save(order);

        // Associa os itens ao pedido salvo e os persiste
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemJPARepository.save(orderItem);
        }

        return savedOrder;
    }

    /**
     * Lista pedidos, removendo aqueles com usuários inválidos.
     *
     * @param pageable Objeto de paginação.
     * @return Paginação de pedidos.
     */
    public Page<ListarOrderDTO> listarOrders(Pageable pageable) {

        limparPedidos();

        // Retorna os pedidos restantes com paginação
        return orderJPARepository.findAll(pageable).map(ListarOrderDTO::new);
    }

    /**
     * Deleta um pedido pelo ID.
     *
     * @param id ID do pedido a ser deletado.
     */
    public void deletarOrder(Long id) {
        orderJPARepository.deleteById(id);
    }

    /**
     * Lista os produtos de um pedido pelo ID.
     *
     * @param id ID do pedido.
     * @return DTO com os produtos do pedido.
     */
    public ListarProductsDeOrderPorIdDTO listarProductsDeOrderPorId(Long id) {
        Order order = orderJPARepository.getReferenceById(id);
        return ListarProductsDeOrderPorIdDTO.toDTO(order);
    }

    /**
     * Lista os pedidos de um usuário específico.
     *
     * @param id ID do usuário.
     * @param pageable Objeto de paginação.
     * @return Paginação de pedidos do usuário.
     */
    public Page<ListarOrderDTO> listarOrdersDeUserPorIdDTO(Long id, Pageable pageable) {

        limparPedidos();

        Page<Order> ordersPage = orderJPARepository.findByUserId(id, pageable);

        return ordersPage.map(ListarOrderDTO::new);
    }

    /**
     * Limpa os pedidos associados a usuários inválidos (não encontrados no sistema).
     * O método filtra e deleta os pedidos com usuários que não existem no banco de dados.
     */
    public void limparPedidos() {
        List<Order> ordersToDelete = orderJPARepository.findAll()
                .stream()
                .filter(order -> !userConsumerService.validarUserPorId(order.getUserId()))
                .collect(Collectors.toList());

        orderJPARepository.deleteAll(ordersToDelete);
    }
}
