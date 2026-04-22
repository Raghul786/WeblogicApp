package com.example.reddit.domain;

import java.util.Optional;

public interface AuthRepository {
    Optional<UserAuth> findByUsername(String username);
}

