package com.myplatform.myplatform.embedded.security;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDetailsService implements UserDetailsService {
    private final Map<String, String> users = new HashMap<>();

    public InMemoryUserDetailsService() {
        users.put("alex", "root");
        users.put("gosha", "grisha");
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        String password = users.get(username);
        if (password != null) {
            return new UserDetailsImpl(username, password);
        }
        return null;
    }

    private static class UserDetailsImpl implements UserDetails {
        private final String username;
        private final String password;

        public UserDetailsImpl(String username, String password) {
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
}
