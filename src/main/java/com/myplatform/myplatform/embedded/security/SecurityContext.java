package com.myplatform.myplatform.embedded.security;

public interface SecurityContext {

    Authentication verify(AuthenticationToken token);

}
