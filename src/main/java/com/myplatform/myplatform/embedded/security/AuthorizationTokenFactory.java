package com.myplatform.myplatform.embedded.security;

import javax.crypto.SecretKey;

public class AuthorizationTokenFactory {

    private final SecretKey secretKey;

    public AuthorizationTokenFactory(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public AuthorizationToken create(AuthenticationToken  token) throws Exception {
        String secret = TokenEncryptor.encrypt(token, secretKey);
        return new AuthorizationToken() {
            @Override
            public String getToken() {
                return secret;
            }
        };
    }

}
