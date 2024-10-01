package com.myplatform.myplatform.embedded.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class ApiKeyGenerator {

    public String generateApiKey() {
        UUID uuid = UUID.randomUUID();
        String apiKey = uuid.toString();
        return Base64.getEncoder().encodeToString(apiKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidApiKey(String apiKey, String other) {
        try {
            String decodedKey = new String(Base64.getDecoder().decode(other), StandardCharsets.UTF_8);
            UUID.fromString(other);
            return apiKey.equals(decodedKey);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}