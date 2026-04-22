package com.example.reddit.infra.dao;

import com.example.reddit.usecase.VoteRepository;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcVoteRepository implements VoteRepository {

    private final DataSource dataSource;

    public JdbcVoteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void upsertPostVote(long userId, long postId, int value) {

        String update =
            "UPDATE reddit_app.votes " +
            "SET value = ? " +
            "WHERE user_id = ? AND post_id = ?";

        String insert =
            "INSERT INTO reddit_app.votes (user_id, post_id, value) " +
            "SELECT ?, ?, ? FROM dual " +
            "WHERE NOT EXISTS (" +
            "  SELECT 1 FROM reddit_app.votes WHERE user_id = ? AND post_id = ?" +
            ")";

        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement psUpdate = conn.prepareStatement(update);
            psUpdate.setInt(1, value);
            psUpdate.setLong(2, userId);
            psUpdate.setLong(3, postId);
            int updated = psUpdate.executeUpdate();

            if (updated == 0) {
                PreparedStatement psInsert = conn.prepareStatement(insert);
                psInsert.setLong(1, userId);
                psInsert.setLong(2, postId);
                psInsert.setInt(3, value);
                psInsert.setLong(4, userId);
                psInsert.setLong(5, postId);
                psInsert.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

