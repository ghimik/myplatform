package com.myplatform.myplatform.service;


import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.security.*;
import com.myplatform.myplatform.model.User;
import com.myplatform.myplatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.UUID;


@Service
public class AuthenticationService {

    public static final String AUTHORIZATION_HEADER = "Authorization";

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

    public String authenticate(String username, String password)
            throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("Trying to login: username=" + username + ", password="+password);
        System.out.println("UserDetails:" + userDetails);

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("Success login!");
            Authentication authentication = new AuthenticationImpl(userDetails);
            return securityContext.setAuthentication(authentication);
        }

        throw new AuthenticationException("Invalid username or password");
    }

    public void register(String username, String password) throws AuthenticationException {
        System.out.println("Started trying registering user");

        if (userDetailsService.loadUserByUsername(username) != null) {
            System.out.println("Registration failed, username already exists");
            throw new AuthenticationException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        System.out.println("New user created: " + newUser);
        userRepository.saveAndFlush(newUser);
    }

    public Authentication getAuthentication(HttpRequest request) {
        String token = request.getHead().getHeaders().getContent().get(AUTHORIZATION_HEADER);
        return securityContext.getAuthentication(token);
    }

}