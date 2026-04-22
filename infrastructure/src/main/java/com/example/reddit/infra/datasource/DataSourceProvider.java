package com.example.reddit.infra.datasource;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceProvider {

    public static DataSource getDataSource() {
        try {
            return (DataSource) new InitialContext()
                    .lookup("java:comp/env/jdbc/RedditDS");
        } catch (Exception e) {
            throw new RuntimeException("JNDI lookup failed", e);
        }
    }
}

