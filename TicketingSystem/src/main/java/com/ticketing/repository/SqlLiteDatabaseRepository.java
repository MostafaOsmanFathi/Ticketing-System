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


}
