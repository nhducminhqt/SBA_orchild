package com.orchid.orchidbe.services.IService;

import com.orchid.orchidbe.pojos.Account;
import com.orchid.orchidbe.pojos.Token;

public interface TokenService {

    Token addToken(String userId, String token);

    Token refreshToken(String refreshToken, Account user) throws Exception;


}
