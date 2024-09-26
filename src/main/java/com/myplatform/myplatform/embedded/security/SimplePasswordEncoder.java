package com.myplatform.myplatform.embedded.security;

import java.util.Base64;

public class SimplePasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        String encodedRawPassword = Base64.getEncoder().encodeToString(rawPassword.getBytes());
        return encodedRawPassword.equals(encodedPassword);
    }
}
