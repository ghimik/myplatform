package com.myplatform.myplatform.embedded.security;

import java.util.HashMap;
import java.util.Map;

public class StaticUserDataService implements UserDataService {

    private final Map<String, String> database = new HashMap<>();

    public StaticUserDataService addUser(String username, String password) {
        database.put(username, password);
        return this;
    }

    @Override
    public UserInfo getUserInfo(String username, String password) {
        if (database.containsKey(username) &&
            database.get(username).equals(password))
            return new BasicUserInfoImpl(username, password);
        return null;
    }
}
