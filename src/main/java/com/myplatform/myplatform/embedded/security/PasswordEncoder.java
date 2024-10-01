package com.myplatform.myplatform.embedded.security;

public interface PasswordEncoder {
    boolean matches(String rawPassword, String encodedPassword);

    String encode(String password);
}
