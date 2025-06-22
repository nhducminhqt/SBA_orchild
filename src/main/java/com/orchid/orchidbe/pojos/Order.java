package com.orchid.orchidbe.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private Integer id; // Use Integer for MongoDB IDs

    private Double totalAmount;

    private Date orderDate;

    private OrderStatus orderStatus;

    private Account account; // Embedded or referenced relationship

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        CANCELLED,
    }
}