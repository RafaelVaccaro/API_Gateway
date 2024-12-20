package com.example.orders_service.domain.service;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderItemDTO;
import com.example.orders_service.infrastructure.entity.*;
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

        return savedOrder;
    }

    public Page<ListarOrderDTO> listarOrders(Pageable pageable) {
        return orderJPARepository.findAll(pageable).map(ListarOrderDTO::new);
    }

    public void concluirOrder(Long id) {
        Order order = orderJPARepository.getReferenceById(id);
        order.setStatus(Status.CONCLUIDO);
        orderJPARepository.save(order);
    }

    public void cancelarOrder(Long id) {
        Order order = orderJPARepository.getReferenceById(id);
        order.setStatus(Status.CANCELADO);
        orderJPARepository.save(order);
    }
}
