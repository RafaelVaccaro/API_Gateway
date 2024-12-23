package com.example.orders_service.domain.service;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
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

    public void registrarOrder(RegistroOrderDTO registroOrderDTO) {


        System.out.println("Verificando usuário com ID: " + registroOrderDTO.userId());
        if (!userConsumerService.validarUserPorId(registroOrderDTO.userId())) {
            throw new UserNotFoundException("User com ID: " + registroOrderDTO.userId() + " não existe");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (RegistroOrderItemDTO itemDTO : registroOrderDTO.orderItems()) {

            if (!productConsumerService.validarProductPorId(itemDTO.productId())) {
                throw new ProductNotFoundException("Product com ID: " + itemDTO.productId() + " não existe");
            }

            productConsumerService.subStock(itemDTO.productId(), itemDTO.quantity());

            double itemPrice = productConsumerService.getProductPrice(itemDTO.productId()) * itemDTO.quantity();
            totalPrice += itemPrice;

            OrderItem orderItem = new OrderItem(itemDTO);
            orderItems.add(orderItem);
        }

        Order order = new Order(registroOrderDTO.userId(), totalPrice, orderItems);

        Order savedOrder = orderJPARepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemJPARepository.save(orderItem);
        }

    }

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


    public void deletarOrder(Long id) {
        orderJPARepository.deleteById(id);
    }
}
