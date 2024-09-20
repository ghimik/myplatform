package com.myplatform.myplatform.embedded.security;

import java.util.HashSet;
import java.util.Set;

public class SecurityContextHolderImpl implements SecurityContextHolder {

    private final Set<Authentication> authentications = new HashSet<>();

    @Override
    public void clear() {
        authentications.clear();
    }

    @Override
    public void addAuthentication(Authentication authentication) {
        authentications.add(authentication);
    }

    @Override
    public void removeAuthentication(Authentication authentication) {
        authentications.remove(authentication);
    }
}
