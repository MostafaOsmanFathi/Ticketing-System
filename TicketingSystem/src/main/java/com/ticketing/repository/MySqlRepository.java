package com.ticketing.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlRepository extends DatabaseRepository {
    private static MySqlRepository instance;
    private String user;
    private String password;

    public static MySqlRepository getInstance() {
        if (instance == null) {
            instance = new MySqlRepository();
            instance.createDatabaseIfNotExists();
        }
        return instance;
    }

    private MySqlRepository() {
        super("schema.sql");
        try {
            loadConfiguration();
            this.connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadConfiguration() {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            this.url = prop.getProperty("jdbc.url");
            this.user = prop.getProperty("jdbc.user");
            this.password = prop.getProperty("jdbc.password");
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load configuration", ex);
        }
    }

}
