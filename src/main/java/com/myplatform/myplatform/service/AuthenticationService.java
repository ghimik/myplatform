package com.myplatform.myplatform.service;


import com.myplatform.myplatform.embedded.security.*;
import com.myplatform.myplatform.model.User;
import com.myplatform.myplatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final SecurityContext securityContext;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserDetailsService userDetailsService,
                                 SecurityContext securityContext,
                                 PasswordEncoder passwordEncoder,
                                 UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.securityContext = securityContext;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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

    public void register(String username, String password) throws AuthenticationException {
        if (userDetailsService.loadUserByUsername(username) != null) {
            throw new AuthenticationException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        userRepository.saveAndFlush(newUser);
    }

    public Authentication getCurrentAuthentication() {
        return securityContext.getAuthentication();
    }
}