package com.orchid.orchidbe.controllers;

import com.orchid.orchidbe.dto.OrderDTO;
import com.orchid.orchidbe.pojos.Order;
import com.orchid.orchidbe.services.IService.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
@Tag(name = "Order Api", description = "Operations related to Order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STAFF')")
    public ResponseEntity<List<OrderDTO.OrderRes>> getOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STAFF')")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STAFF')")
    public ResponseEntity<Void> addOrder(@RequestBody Order order) {
        orderService.add(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateOrder(@PathVariable String id, @RequestBody Order order) {
        order.setId(id);
        orderService.update(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<OrderDTO.OrderRes>> getMyOrders(@RequestParam String accountId) {
        return ResponseEntity.ok(orderService.getOrdersByAccountId(accountId));
    }
}