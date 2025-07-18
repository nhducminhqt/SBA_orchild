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
    private String id; // MongoDB uses String for IDs

    private Double totalAmount;

    private Date orderDate;

    private OrderStatus orderStatus;

    private Account account; // Embedded or referenced relationship

    private String address; // New field for address

    private String phoneNumber; // New field for phone number

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        CANCELLED,
    }
}