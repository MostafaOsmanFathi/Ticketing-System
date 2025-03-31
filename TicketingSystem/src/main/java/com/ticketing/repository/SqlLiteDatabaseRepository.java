package com.ticketing.repository;

import java.io.File;
import java.sql.*;

public class SqlLiteDatabaseRepository extends DatabaseRepository {
    private static SqlLiteDatabaseRepository instance;
    private String user;
    private String password;

    public static SqlLiteDatabaseRepository getInstance() {
        if (instance == null) {
            instance = new SqlLiteDatabaseRepository();
            checkAndInitializeDatabase(instance.connection);
        }
        return instance;
    }

    public static SqlLiteDatabaseRepository getInstance(String filePath) {
        if (instance == null) {
            instance = new SqlLiteDatabaseRepository(filePath);
            if (!fileExists(filePath))
                instance.resetDatabase();
        }
        return instance;
    }

    private SqlLiteDatabaseRepository() {
        super("sqliteSchema.sql");
        try {
            url = "jdbc:sqlite:TicketingSystem.db";
            this.connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SqlLiteDatabaseRepository(String filePath) {
        super("sqliteSchema.sql");
        try {
            url = "jdbc:sqlite:" + filePath;
            this.connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void checkAndInitializeDatabase(Connection conn) {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.next()) { // If no table exists, call your function
                instance.resetDatabase(); // Replace this with your function
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
