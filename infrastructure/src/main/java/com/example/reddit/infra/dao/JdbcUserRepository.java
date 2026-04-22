package com.example.reddit.infra.dao;

import com.example.reddit.domain.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUserRepository implements UserRepository {

    private final DataSource ds;

    public JdbcUserRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public int countUsers() {

        String sql = "SELECT COUNT(*) FROM reddit_app.users";

        try (
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            throw new RuntimeException("Failed to count users", e);
        }
    }

    // 🔎 NEW — fetch user_id by username (used by VoteServlet)
    @Override
    public long findUserIdByUsername(String username) {

        String sql = "SELECT user_id FROM reddit_app.users WHERE username = ?";

        try (
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            }

            throw new RuntimeException("User not found: " + username);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user id", e);
        }
    }
}

