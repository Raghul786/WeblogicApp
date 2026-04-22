package com.example.reddit.domain;

public class UserAuth {

    private final String username;
    private final String passwordHash;

    public UserAuth(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}

