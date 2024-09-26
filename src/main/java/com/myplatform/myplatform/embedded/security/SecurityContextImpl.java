package com.myplatform.myplatform.embedded.security;

public class SecurityContextImpl implements SecurityContext {
    private Authentication authentication;

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }
}
