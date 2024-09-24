package com.myplatform.myplatform.embedded.security;

public interface SecurityContext {

    void addAuthentication(Authentication authentication);

    Boolean verify(AuthenticationToken token);

}
