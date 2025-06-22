package com.orchid.orchidbe.services.IService;

import com.orchid.orchidbe.dto.AuthPort;
import com.orchid.orchidbe.dto.TokenPort;

public interface AuthService {

    AuthPort.LoginResponse refreshToken(TokenPort.RefreshTokenDTO refreshTokenDTO) throws Exception;

}
