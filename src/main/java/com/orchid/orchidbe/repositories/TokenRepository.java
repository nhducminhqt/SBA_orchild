package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TokenRepository extends MongoRepository<Token, Integer> {

    List<Token> findByAccountId(Integer accountId);

    Optional<Token> findByToken(String token);

    Optional<Token> findByRefreshToken(String token);



}
