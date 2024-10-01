package com.myplatform.myplatform.embedded.security;

import java.util.HashMap;
import java.util.UUID;

public class SecurityContextImpl implements SecurityContext {

    private final ApiKeyGenerator apiKeyGenerator;

    public SecurityContextImpl(ApiKeyGenerator apiKeyGenerator) {
        this.apiKeyGenerator = apiKeyGenerator;
    }

    private final HashMap<String, Authentication> authenticationHashMap =
            new HashMap<>();

    @Override
    public String setAuthentication(Authentication authentication) {
        String uuid = apiKeyGenerator.generateApiKey();
        authenticationHashMap.put(uuid, authentication);
        return uuid;
    }

    @Override
    public Authentication getAuthentication(String uuid) {
        return authenticationHashMap.get(uuid);
    }

    @Override
    public void removeAuthentication(String token) {
        authenticationHashMap.remove(token);
    }
}
