package com.example.reddit.usecase;

import com.example.reddit.domain.AuthRepository;
import com.example.reddit.domain.UserAuth;

import java.util.Optional;

public class LoginUseCase {

    private final AuthRepository repository;
    private final PasswordHasher hasher;

    public LoginUseCase(AuthRepository repository, PasswordHasher hasher) {
        this.repository = repository;
        this.hasher = hasher;
    }

    public boolean authenticate(String username, String password) {

        Optional<UserAuth> auth = repository.findByUsername(username);

        if (auth.isEmpty()) {
            return false;
        }

        return hasher.verify(password, auth.get().getPasswordHash());
    }
}

