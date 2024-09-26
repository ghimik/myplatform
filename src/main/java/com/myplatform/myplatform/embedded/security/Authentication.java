package com.myplatform.myplatform.embedded.security;

public interface Authentication {
    UserDetails getPrincipal();
    boolean isAuthenticated();
}
