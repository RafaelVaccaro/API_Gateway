package com.example.orders_service.domain.service;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import com.example.orders_service.infrastructure.entity.Order;
import com.example.orders_service.infrastructure.entity.OrderItem;
import com.example.orders_service.infrastructure.entity.ProductNotFoundException;
import com.example.orders_service.infrastructure.entity.UserNotFoundException;
import com.example.orders_service.infrastructure.repository.OrderItemJPARepository;
import com.example.orders_service.infrastructure.repository.OrderJPARepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJPARepository orderJPARepository;
    private final OrderItemJPARepository orderItemJPARepository;
    private final ProductConsumerService productConsumerService;
    private final UserConsumerService userConsumerService;

    public Order registrarOrder(RegistroOrderDTO registroOrderDTO) {

        // Valida o usuário
        if (!userConsumerService.validarUserPorId(registroOrderDTO.userId())) {
            throw new UserNotFoundException("User com ID: " + registroOrderDTO.userId() + " não existe");
        }

        // Inicializa a lista de itens e o preço total
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (RegistroOrderItemDTO itemDTO : registroOrderDTO.orderItems()) {

            // Valida o produto
            if (!productConsumerService.validarProductPorId(itemDTO.productId())) {
                throw new ProductNotFoundException("Product com ID: " + itemDTO.productId() + " não existe");
            }

            // Calcula o preço do item
            double itemPrice = productConsumerService.getProductPrice(itemDTO.productId()) * itemDTO.quantity();
            totalPrice += itemPrice;

            // Cria o OrderItem
            OrderItem orderItem = new OrderItem(itemDTO);
            orderItems.add(orderItem);
        }

        // Cria o pedido
        Order order = new Order(registroOrderDTO.userId(), totalPrice, orderItems);

        // Salva o pedido para obter ID e associa os itens
        Order savedOrder = orderJPARepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder); // Associação explícita
            orderItemJPARepository.save(orderItem);
        }

        return savedOrder;
    }

    public Page<ListarOrderDTO> listarOrders(Pageable pageable) {
        return orderJPARepository.findAll(pageable).map(ListarOrderDTO::new);
    }
}
