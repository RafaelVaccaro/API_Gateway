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

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJPARepository orderJPARepository;
    private final OrderItemJPARepository orderItemJPARepository;
    private final ProductConsumerService productConsumerService;
    private final UserConsumerService userConsumerService;

    /**
     * Registra um pedido, validando usuário e produtos.
     *
     * @param registroOrderDTO Dados para registrar o pedido.
     * @return O pedido registrado.
     */
    public Order registrarOrder(RegistroOrderDTO registroOrderDTO) {

        System.out.println("Verificando usuário com ID: " + registroOrderDTO.userId());
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
        // Filtra e deleta pedidos com usuários inválidos diretamente no banco de dados
        List<Order> ordersToDelete = orderJPARepository.findAll()
                .stream()
                .filter(order -> !userConsumerService.validarUserPorId(order.getUserId()))
                .collect(Collectors.toList());

        // Deleta os pedidos com usuários inválidos
        orderJPARepository.deleteAll(ordersToDelete);

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
        Page<Order> ordersPage = orderJPARepository.findByUserId(id, pageable);

        return ordersPage.map(ListarOrderDTO::new);
    }
}
