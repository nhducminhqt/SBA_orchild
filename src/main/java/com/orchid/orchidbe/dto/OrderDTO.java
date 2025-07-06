package com.orchid.orchidbe.dto;

import com.orchid.orchidbe.pojos.Order;
import com.orchid.orchidbe.pojos.Order.OrderStatus;
import java.util.Date;

public interface OrderDTO {

    record OrderReq(
            Double totalAmount,
            Date orderDate,
            OrderStatus orderStatus,
            String accountId,
            String address, // New field
            String phoneNumber // New field
    ) {
    }

    record OrderRes(
            String id,
            Double totalAmount,
            Date orderDate,
            OrderStatus orderStatus,
            String accountId,
            String address, // New field
            String phoneNumber // New field
    ) {
        public static OrderRes fromEntity(Order order) {
            return new OrderRes(
                    order.getId(),
                    order.getTotalAmount(),
                    order.getOrderDate(),
                    order.getOrderStatus(),
                    order.getAccount() != null ? order.getAccount().getId() : null,
                    order.getAddress(), // Map address
                    order.getPhoneNumber() // Map phone number
            );
        }
    }
}