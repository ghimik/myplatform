package com.myplatform.myplatform.service;

import com.myplatform.myplatform.PseudoBeanConfig;
import com.myplatform.myplatform.embedded.security.*;

import javax.naming.AuthenticationException;

public class AuthService {

    private final SecurityContext securityContextHolder
            = PseudoBeanConfig.securityContextHolder;

    private final UserDataService userDataService
            = PseudoBeanConfig.userDataService;

    private final AuthorizationTokenFactory tokenFactory
            = PseudoBeanConfig.authorizationTokenFactory;

    public AuthorizationToken authorize(String username, String password)
            throws Exception {
        Authentication authentication = new AuthenticationImpl(userDataService);
        AuthenticationToken authenticationToken =
                new UsernameAndPasswordAuthenticationToken(username, password);

        Authentication handled = authentication.authenticate(authenticationToken);
        if (handled.getStatus()) {
            securityContextHolder.addAuthentication(handled);
            return tokenFactory.create(authenticationToken);
        }
        else
            throw new AuthenticationException("Authentication failed");
    }

}
