package com.orchid.orchidbe.dto;

import com.orchid.orchidbe.pojos.Order.OrderStatus;
import com.orchid.orchidbe.pojos.Order;
import java.util.Date;

public interface OrderDTO {

    record OrderReq(
            Double totalAmount,
            Date orderDate,
            OrderStatus orderStatus,
            String accountId // Only the account ID is required
    ) {
    }

    record OrderRes(
            String id,
            Double totalAmount,
            Date orderDate,
            OrderStatus orderStatus,
            String accountId
    ) {
        public static OrderRes fromEntity(Order order) {
            return new OrderRes(
                    order.getId(),
                    order.getTotalAmount(),
                    order.getOrderDate(),
                    order.getOrderStatus(),
                    order.getAccount() != null ? order.getAccount().getId() : null
            );
        }
    }
}