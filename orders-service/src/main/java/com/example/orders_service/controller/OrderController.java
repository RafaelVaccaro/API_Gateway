package com.example.orders_service.controller;

import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.service.OrderService;
import com.example.orders_service.domain.service.ProductConsumerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Transactional
    public void registrarOrder(RegistroOrderDTO registroOrderDTO) {
        orderService.registrarOrder(registroOrderDTO);
    }
}
