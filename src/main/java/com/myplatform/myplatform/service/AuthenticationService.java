package com.myplatform.myplatform.service;


import com.myplatform.myplatform.embedded.security.*;

import javax.naming.AuthenticationException;

public class AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final SecurityContext securityContext;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserDetailsService userDetailsService,
                                 SecurityContext securityContext,
                                 PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.securityContext = securityContext;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication authenticate(String username, String password)
            throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            Authentication authentication = new AuthenticationImpl(userDetails);
            securityContext.setAuthentication(authentication);
            return authentication;
        }

        throw new AuthenticationException("Invalid username or password");
    }

    public Authentication getCurrentAuthentication() {
        return securityContext.getAuthentication();
    }
}