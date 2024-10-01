package com.myplatform.myplatform.service;

import com.myplatform.myplatform.embedded.security.UserDetails;
import com.myplatform.myplatform.embedded.security.UserDetailsService;
import com.myplatform.myplatform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
