package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Orchid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrchidRepository extends MongoRepository<Orchid, Integer> {
}
