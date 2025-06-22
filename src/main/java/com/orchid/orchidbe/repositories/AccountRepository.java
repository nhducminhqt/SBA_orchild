package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, int id);
    Optional<Account> findByEmail(String email);

}