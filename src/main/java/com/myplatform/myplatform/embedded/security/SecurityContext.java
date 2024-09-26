package com.myplatform.myplatform.embedded.security;

public interface SecurityContext {
    void setAuthentication(Authentication authentication);
    Authentication getAuthentication();
}


