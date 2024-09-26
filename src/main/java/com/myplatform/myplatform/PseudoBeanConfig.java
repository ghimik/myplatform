package com.myplatform.myplatform;

import com.myplatform.myplatform.embedded.security.*;
import com.myplatform.myplatform.service.AuthenticationService;

public class PseudoBeanConfig {

    private static final SecurityContext securityContext
            = new SecurityContextImpl();
    private static final UserDetailsService userDetailsService
            = new InMemoryUserDetailsService();
    private static final PasswordEncoder passwordEncoder
            = new SimplePasswordEncoder();
    private static final AuthenticationService authenticationService
            = new AuthenticationService(userDetailsService, securityContext, passwordEncoder);

    public static SecurityContext getSecurityContext() {
        return securityContext;
    }

    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
