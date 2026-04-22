package com.example.reddit.infra.dao;

import com.example.reddit.domain.AuthRepository;
import com.example.reddit.domain.UserAuth;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class JdbcAuthRepository implements AuthRepository {

    private final DataSource dataSource;

    public JdbcAuthRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<UserAuth> findByUsername(String username) {

        String sql = "SELECT username, password_hash FROM users WHERE username = ?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(
                new UserAuth(
                    rs.getString("username"),
                    rs.getString("password_hash")
                )
            );

        } catch (SQLException e) {
            throw new RuntimeException("Failed fetching user auth", e);
        }
    }
}

