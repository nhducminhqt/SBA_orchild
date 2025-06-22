package com.orchid.orchidbe.services.ImplService;

import com.orchid.orchidbe.dto.AuthPort;
import com.orchid.orchidbe.dto.AuthPort.LoginResponse;
import com.orchid.orchidbe.dto.TokenPort.RefreshTokenDTO;
import com.orchid.orchidbe.dto.TokenPort.TokenResponse;
import com.orchid.orchidbe.pojos.Account;
import com.orchid.orchidbe.pojos.Token;
import com.orchid.orchidbe.services.IService.AccountService;
import com.orchid.orchidbe.services.IService.AuthService;
import com.orchid.orchidbe.services.IService.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountService userService;
    private final TokenService tokenService;


    @Override
    public LoginResponse refreshToken(RefreshTokenDTO refreshTokenDTO) throws Exception {
        Account userDetail = userService.getUserDetailsFromRefreshToken(
            refreshTokenDTO.refreshToken());
        Token jwtToken = tokenService.refreshToken(refreshTokenDTO.refreshToken(), userDetail);
        return new AuthPort.LoginResponse(
            new TokenResponse(
                jwtToken.getId(),
                jwtToken.getToken(),
                jwtToken.getRefreshToken(),
                jwtToken.getTokenType(),
                jwtToken.getExpirationDate(),
                jwtToken.getRefreshExpirationDate(),
                jwtToken.isRevoked(),
                jwtToken.isExpired())
        );
    }
}
