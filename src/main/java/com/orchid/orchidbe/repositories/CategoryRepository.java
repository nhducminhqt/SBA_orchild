package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, int id);

    Optional<Category> findByName(String name);
}