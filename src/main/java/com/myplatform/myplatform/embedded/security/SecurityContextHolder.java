package com.myplatform.myplatform.embedded.security;

public interface SecurityContextHolder {

    void clear();

    void addAuthentication(Authentication authentication);

    void removeAuthentication(Authentication authentication);

}
