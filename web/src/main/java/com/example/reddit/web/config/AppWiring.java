package com.example.reddit.web.config;

import com.example.reddit.usecase.UserService;
import com.example.reddit.usecase.HealthService;
import com.example.reddit.usecase.LoginUseCase;
import com.example.reddit.usecase.FeedUseCase;
import com.example.reddit.usecase.VoteUseCase;

import com.example.reddit.infra.dao.JdbcUserRepository;
import com.example.reddit.infra.dao.JdbcAuthRepository;
import com.example.reddit.infra.dao.JdbcFeedRepository;
import com.example.reddit.infra.dao.JdbcVoteRepository;

import com.example.reddit.infra.datasource.DataSourceProvider;
import com.example.reddit.infra.security.BCryptPasswordHasher;

public class AppWiring {

    public static LoginUseCase loginUseCase() {
        return new LoginUseCase(
            new JdbcAuthRepository(DataSourceProvider.getDataSource()),
            new BCryptPasswordHasher()
        );
    }

    public static UserService userService() {
        return new UserService(
            new JdbcUserRepository(
                DataSourceProvider.getDataSource()
            )
        );
    }

    public static HealthService healthService() {
        return new HealthService(
            new JdbcUserRepository(
                DataSourceProvider.getDataSource()
            )
        );
    }

    // 📊 Feed (read path)
    public static FeedUseCase feedUseCase() {
        return new FeedUseCase(
            new JdbcFeedRepository(
                DataSourceProvider.getDataSource()
            )
        );
    }

    // 🗳 Vote (write path)
    public static VoteUseCase voteUseCase() {
        return new VoteUseCase(
            new JdbcVoteRepository(
                DataSourceProvider.getDataSource()
            )
        );
    }
}

