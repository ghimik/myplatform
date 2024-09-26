package com.myplatform.myplatform.embedded.security;

public class AuthenticationImpl implements Authentication {
    private final UserDetails principal;
    private final boolean authenticated;

    AuthenticationImpl(UserDetails principal, boolean authenticated) {
        this.principal = principal;
        this.authenticated = authenticated;
    }

    public AuthenticationImpl(UserDetails principal) {
        this.principal = principal;
        authenticated = false;
    }

    @Override
    public UserDetails getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
}
