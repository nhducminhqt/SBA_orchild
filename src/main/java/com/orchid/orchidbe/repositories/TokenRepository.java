package com.orchid.orchidbe.repositories;

import com.orchid.orchidbe.pojos.Token;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findByAccountId(Integer accountId);

    Optional<Token> findByToken(String token);

    Optional<Token> findByRefreshToken(String token);



}
