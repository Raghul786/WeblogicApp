package com.example.reddit.usecase;

public interface PasswordHasher {
    String hash(String password);
    boolean verify(String password, String hash);
}

