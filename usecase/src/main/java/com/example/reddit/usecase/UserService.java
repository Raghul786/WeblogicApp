package com.example.reddit.usecase;

import com.example.reddit.domain.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int getUserCount() {
        return userRepository.countUsers();
    }

    // 🔎 NEW — used by VoteServlet to map session user → DB user_id
    public long findUserIdByUsername(String username) {
        return userRepository.findUserIdByUsername(username);
    }
}

