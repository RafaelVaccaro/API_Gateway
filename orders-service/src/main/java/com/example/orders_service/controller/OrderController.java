package com.example.orders_service.controller;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Transactional
    public void registrarOrder(@RequestBody @Valid RegistroOrderDTO registroOrderDTO) {
        orderService.registrarOrder(registroOrderDTO);
    }

    @GetMapping
    public Page<ListarOrderDTO> listarOrders(Pageable pageable) {
        return orderService.listarOrders(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletarOrder(@PathVariable Long id) {
        orderService.deletarOrder(id);
    }
}
