package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    boolean existsByName(String name);

    Optional<Role> findByName(String name);

}