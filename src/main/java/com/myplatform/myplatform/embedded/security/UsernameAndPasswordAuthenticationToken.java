package com.myplatform.myplatform.embedded.security;

public class UsernameAndPasswordAuthenticationToken implements AuthenticationToken {

    final String username;

    final String password;

    public UsernameAndPasswordAuthenticationToken(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public UserInfo getUserInfo() {
        return new BasicUserInfoImpl(username, password);
    }
}
