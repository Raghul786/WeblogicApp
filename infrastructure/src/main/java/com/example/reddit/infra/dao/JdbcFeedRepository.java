package com.example.reddit.infra.dao;

import com.example.reddit.usecase.FeedRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JdbcFeedRepository implements FeedRepository {

    private final DataSource dataSource;

    public JdbcFeedRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Map<String,Object>> fetchCommunityFeed(long communityId) {

        String sql =
            "SELECT p.post_id, p.title, p.created_at, " +
            "       COALESCE(v.score,0) AS score " +
            "FROM reddit_app.posts p " +
            "LEFT JOIN ( " +
            "    SELECT post_id, SUM(value) score " +
            "    FROM reddit_app.votes " +
            "    GROUP BY post_id " +
            ") v ON p.post_id = v.post_id " +
            "WHERE p.community_id = ? " +
            "ORDER BY p.created_at DESC " +
            "FETCH FIRST 20 ROWS ONLY";

        List<Map<String,Object>> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, communityId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String,Object> row = new HashMap<>();
                row.put("postId", rs.getLong(1));
                row.put("title", rs.getString(2));
                row.put("createdAt", rs.getTimestamp(3));
                row.put("score", rs.getInt(4));
                results.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}

