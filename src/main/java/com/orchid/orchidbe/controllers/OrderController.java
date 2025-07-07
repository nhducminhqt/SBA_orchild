package com.orchid.orchidbe.controllers;

import com.orchid.orchidbe.dto.OrderDTO;
import com.orchid.orchidbe.pojos.Account;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    public ResponseEntity<Void> addOrder(@RequestBody OrderDTO.OrderReq orderReq) {
        Order order = Order.builder()
                .totalAmount(orderReq.totalAmount())
                .orderDate(orderReq.orderDate())
                .orderStatus(orderReq.orderStatus())
                .account(Account.builder().id(orderReq.accountId()).build())
                .address(orderReq.address()) // Set address
                .phoneNumber(orderReq.phoneNumber()) // Set phone number
                .build();
        orderService.add(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateOrder(@PathVariable String id, @RequestBody OrderDTO.OrderReq orderReq) {
        Order order = Order.builder()
                .id(id) // Set the ID for the update
                .totalAmount(orderReq.totalAmount())
                .orderDate(orderReq.orderDate())
                .orderStatus(orderReq.orderStatus())
                .account(Account.builder().id(orderReq.accountId()).build())
                .address(orderReq.address())
                .phoneNumber(orderReq.phoneNumber())
                .build();
        orderService.update(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_STAFF')")
    public ResponseEntity<Void> deleteOrder(@RequestBody OrderDTO.OrderReq orderReq) {
        orderService.delete(orderReq.accountId()); // Use accountId or another unique identifier
        return ResponseEntity.ok().build();
    }
    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<OrderDTO.OrderRes>> getMyOrders(@RequestParam String accountId) {
        return ResponseEntity.ok(orderService.getOrdersByAccountId(accountId));
    }
}