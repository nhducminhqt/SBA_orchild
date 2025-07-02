package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByAccountId(String accountId); // New query method
}