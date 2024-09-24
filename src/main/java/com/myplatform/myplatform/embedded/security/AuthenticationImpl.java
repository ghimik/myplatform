package com.myplatform.myplatform.embedded.security;

public class AuthenticationImpl implements Authentication {

    private final Boolean status;

    private final UserDataService service;

    public AuthenticationImpl(UserDataService service) {
        this.service = service;
        this.status = false;
    }

    AuthenticationImpl(UserDataService service, Boolean status) {
        this.service = service;
        this.status = status;
    }

    @Override
    public Authentication authenticate(AuthenticationToken token) {
        UserInfo userInfo = token.getUserInfo();

        if (userInfo.equals(
                service.getUserInfo(userInfo.getUsername(), userInfo.getPassword()))
        )
            return new AuthenticationImpl(this.service, true);

        return new AuthenticationImpl(this.service, false);
    }

    @Override
    public Boolean getStatus() {
        return this.status;
    }
}
