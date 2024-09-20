package com.myplatform.myplatform.embedded.security;

public class BasicUserInfoImpl implements UserInfo {

    String username;

    String password;

    public BasicUserInfoImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
