package com.myplatform.myplatform.embedded.security;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
