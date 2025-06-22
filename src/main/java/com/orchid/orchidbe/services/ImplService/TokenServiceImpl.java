package com.orchid.orchidbe.services.ImplService;

import com.orchid.orchidbe.components.JwtTokenUtils;
import com.orchid.orchidbe.exceptions.ExpiredTokenException;
import com.orchid.orchidbe.exceptions.TokenNotFoundException;
import com.orchid.orchidbe.pojos.Account;
import com.orchid.orchidbe.pojos.Token;
import com.orchid.orchidbe.repositories.TokenRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.orchid.orchidbe.services.IService.AccountService;
import com.orchid.orchidbe.services.IService.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private static final int MAX_TOKENS = 3;
    private final AccountService accountService;
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.expiration-refresh-token}")
    private int expirationRefreshToken;

    private final TokenRepository tokenRepository;
    private final JwtTokenUtils jwtTokenUtil;

    @Transactional
    @Override
    public Token refreshToken(String refreshToken, Account user) throws Exception {
        Token existingToken = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenNotFoundException("Refresh token does not exist"));

        if (existingToken.getRefreshExpirationDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(existingToken);
            throw new ExpiredTokenException("Refresh token is expired");
        }
        String token = jwtTokenUtil.generateToken(user);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expiration);
        existingToken.setExpirationDate(expirationDateTime);
        existingToken.setToken(token);
        existingToken.setRefreshToken(UUID.randomUUID().toString());
        existingToken.setRefreshExpirationDate(
                LocalDateTime.now().plusSeconds(expirationRefreshToken));
        return existingToken;
    }

    @Transactional
    @Override
    public Token addToken(String userId, String token) {
        Account existingUser = accountService.getById(userId);
        List<Token> userTokens = tokenRepository.findByAccountId(existingUser.getId());
        int tokenCount = userTokens.size();

        if (tokenCount >= MAX_TOKENS) {
            Token tokenToDelete = userTokens.get(0);
            tokenRepository.delete(tokenToDelete);
        }

        long expirationInSeconds = expiration;
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expirationInSeconds);

        Token newToken = Token.builder()
                .account(existingUser)
                .token(token)
                .revoked(false)
                .expired(false)
                .tokenType("Bearer")
                .expirationDate(expirationDateTime)
                .build();

        newToken.setRefreshToken(UUID.randomUUID().toString());
        newToken.setRefreshExpirationDate(LocalDateTime.now().plusSeconds(expirationRefreshToken));
        tokenRepository.save(newToken);
        return newToken;
    }
}