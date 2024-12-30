package com.example.orders_service.controller;

import com.example.orders_service.domain.dto.ListarOrderDTO;
import com.example.orders_service.domain.dto.ListarProductsDeOrderPorIdDTO;
import com.example.orders_service.domain.dto.RegistroOrderDTO;
import com.example.orders_service.domain.service.OrderService;
import com.example.orders_service.infrastructure.entity.Order;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela gestão de pedidos.
 * Exposição de endpoints REST para operações CRUD de pedidos.
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Endpoint para registrar um novo pedido.
     *
     * @param registroOrderDTO Dados do pedido a ser registrado.
     * @return O pedido registrado.
     */
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Order registrarOrder(@RequestBody @Valid RegistroOrderDTO registroOrderDTO) {
        return orderService.registrarOrder(registroOrderDTO);
    }

    /**
     * Endpoint para listar os pedidos com paginação.
     *
     * @param pageable Paginação dos pedidos.
     * @return Página de pedidos.
     */
    @GetMapping
    public Page<ListarOrderDTO> listarOrders(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return orderService.listarOrders(pageable);
    }

    /**
     * Endpoint para deletar um pedido pelo seu ID.
     *
     * @param id ID do pedido a ser deletado.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarOrder(@PathVariable Long id) {
        orderService.deletarOrder(id);
    }

    /**
     * Endpoint para listar os produtos de um pedido específico.
     * @param id ID do pedido.
     * @return Dados dos produtos do pedido.
     */
    @GetMapping("/produtosDePedido/{id}")
    public ListarProductsDeOrderPorIdDTO listarProductsDeOrderPorId(@PathVariable Long id) {
        return orderService.listarProductsDeOrderPorId(id);
    }

    /**
     * Endpoint para listar os pedidos de um usuário específico com paginação.
     * @param id ID do usuário.
     * @param paginacao Dados de paginação.
     * @return Página de pedidos do usuário.
     */
    @GetMapping("/pedidosDeUsuario/{id}")
    public Page<ListarOrderDTO> listarPedidosDeUsuarioPorId(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return orderService.listarOrdersDeUserPorIdDTO(id, paginacao);
    }
}
