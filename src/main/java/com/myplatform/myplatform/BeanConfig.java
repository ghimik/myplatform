package com.myplatform.myplatform;

import com.myplatform.myplatform.embedded.security.*;
import com.myplatform.myplatform.repo.UserRepository;
import com.myplatform.myplatform.service.AuthenticationService;
import com.myplatform.myplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class BeanConfig {

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContextImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public AuthenticationService authenticationService(UserDetailsService userDetailsService,
                                                       SecurityContext securityContext,
                                                       PasswordEncoder passwordEncoder,
                                                       @Autowired UserRepository userRepository) {
        return new AuthenticationService(userDetailsService, securityContext,
                passwordEncoder, userRepository);
    }

}
