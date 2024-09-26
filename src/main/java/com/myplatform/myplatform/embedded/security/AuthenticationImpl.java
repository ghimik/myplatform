package com.myplatform.myplatform.embedded.security;

public class AuthenticationImpl implements Authentication {
    private final UserDetails principal;
    private final boolean authenticated;

    public AuthenticationImpl(UserDetails principal, boolean authenticated) {
        this.principal = principal;
        this.authenticated = authenticated;
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
