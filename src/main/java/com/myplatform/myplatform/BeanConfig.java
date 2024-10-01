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
@ComponentScan(basePackages = {
        "com.myplatform.myplatform.service",
        "com.myplatform.myplatform.repo",
        "com.myplatform.myplatform.endpoints",
        "com.myplatform.myplatform"
})
public class BeanConfig {

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContextImpl(apiKeyGenerator());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public ApiKeyGenerator apiKeyGenerator() {
        return new ApiKeyGenerator();
    }

}
