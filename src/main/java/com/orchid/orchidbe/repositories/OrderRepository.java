package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Integer> {
}