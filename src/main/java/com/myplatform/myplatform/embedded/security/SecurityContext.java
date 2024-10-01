package com.myplatform.myplatform.embedded.security;

import java.util.UUID;


// TODO сделать не строкой а каким-то красивым классом и фабрикой возможно
public interface SecurityContext {
    String setAuthentication(Authentication authentication);
    Authentication getAuthentication(String uuid);
    void removeAuthentication(String token);
}


