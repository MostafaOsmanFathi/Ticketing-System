package com.ticketing.repository;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlLiteDatabaseRepository extends DatabaseRepository {
    private static SqlLiteDatabaseRepository instance;
    private String user;
    private String password;

    public static SqlLiteDatabaseRepository getInstance() {
        if (instance == null) {
            instance = new SqlLiteDatabaseRepository();
            if (!fileExists("TicketingSystem.db"))
                instance.resetDatabase();
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

}
