package com.myplatform.myplatform.embedded.security;

public interface UserDetails {
    String getUsername();
    String getPassword();
    // Другие методы, например, для получения ролей
}
