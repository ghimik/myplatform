package com.myplatform.myplatform.embedded.security;

public class AuthenticationTokenImpl implements AuthenticationToken {
    private final UserDetails userDetails;

    public AuthenticationTokenImpl(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public UserDetails getUserDetails() {
        return userDetails;
    }
}
