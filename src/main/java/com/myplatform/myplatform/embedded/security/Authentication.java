package com.myplatform.myplatform.embedded.security;

public interface Authentication {

    Authentication authenticate(AuthenticationToken token);

    Boolean getStatus();

}
