package com.ticketing.repository;

import com.ticketing.enums.AccountType;
import com.ticketing.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public abstract class DatabaseRepository implements AccountRepository, TicketingRepository {
    protected String url;
    protected Connection connection;

    @Override
    public boolean createAccount(Account account, String accountType) {
        String insertAccountSQL = "INSERT INTO Account (idAccount, name, email, password, balance) VALUES (?, ?, ?, ?, ?)";
        String insertRoleSQL = accountType.equalsIgnoreCase("Customer") ? "INSERT INTO Customer (idCustomer, Account_idAccount) VALUES (?, ?)" : "INSERT INTO EventOrganizer (idEventOrganizer, Account_idAccount) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertAccountSQL); PreparedStatement stmtRole = connection.prepareStatement(insertRoleSQL)) {
            connection.setAutoCommit(false);

            stmt.setInt(1, account.getAccountId());
            stmt.setString(2, account.getUserName());
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getPassword());
            stmt.setDouble(5, account.getWalletBalance());
            stmt.executeUpdate();

            stmtRole.setInt(1, account.getAccountId());
            stmtRole.setInt(2, account.getAccountId());
            stmtRole.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public Account getAccount(String email, String password) {
        String sql = "SELECT * FROM Account WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Account account = new Account(rs.getInt("idAccount"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                double walletBalance = rs.getDouble("balance");
                if (walletBalance != 0)
                    account.deposit(walletBalance);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountType getAccountType(int accountId) {
        String sql = "SELECT * FROM Customer WHERE Account_idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return AccountType.Customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AccountType.EventOrganizer;
    }

    public int getCustomerId(int accountId) {
        String sql = "SELECT * FROM Customer WHERE Account_idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idCustomer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getEventOrganizerId(int accountId) {
        String sql = "SELECT * FROM EventOrganizer WHERE Account_idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idEventOrganizer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean depositToAccountByAccountId(int accountId, double amount) {
        String sql = "UPDATE Account SET balance = balance + ? WHERE idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);

            int effectedRows = stmt.executeUpdate();
            connection.commit();
            return effectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean withdrawAccountById(int accountId, double amount) {
        String sql = "UPDATE Account SET balance = balance - ? WHERE idAccount = ? AND balance >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.setDouble(3, amount);
            int effectedRows = stmt.executeUpdate();
            connection.commit();
            return effectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createEvent(EventOrganizer eventOrganizer, Event event) {
        String query = "INSERT INTO Event (idEvent, eventName, eventType, eventDescription, EventOrganizer_idEventOrganizer) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, event.getEventId());
            stmt.setString(2, event.getEventName());
            stmt.setString(3, event.getEventType());
            stmt.setString(4, event.getEventDescription());
            stmt.setInt(5, eventOrganizer.getEventOrganizerId());
            boolean effectedRows = stmt.executeUpdate() > 0;
            connection.commit();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Event getEvent(int eventOrganizerId, int eventId) {
        String query = "SELECT * FROM Event WHERE idEvent = ? AND EventOrganizer_idEventOrganizer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            stmt.setInt(2, eventOrganizerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Event(rs.getInt("idEvent"), rs.getInt("EventOrganizer_idEventOrganizer"), rs.getString("eventName"), rs.getString("eventType"), rs.getString("eventDescription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createTicketType(Event event, TicketType ticketType) {
        String sql = "INSERT INTO TicketType " + "(idTicketType, ticketPrice, ticketName, numberOfTickets, Event_idEvent) " + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ticketType.getTicketTypeId());
            ps.setDouble(2, ticketType.getTicketPrice());
            ps.setString(3, ticketType.getTicketName());
            ps.setInt(4, ticketType.getNumberOfTickets());
            ps.setInt(5, event.getEventId());

            int rowsAffected = ps.executeUpdate();
            connection.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public TicketType getTicketType(int eventId, int ticketTypeId) {
        String sql = "SELECT * FROM TicketType WHERE Event_idEvent = ? AND idTicketType = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setInt(2, ticketTypeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int ticketId = rs.getInt("idTicketType");
                    double ticketPrice = rs.getDouble("ticketPrice");
                    String ticketName = rs.getString("ticketName");
                    int numberOfTickets = rs.getInt("numberOfTickets");
                    int eventEventOrganizerId = rs.getInt("EventOrganizer_id");

                    return new TicketType(eventId, eventEventOrganizerId, ticketTypeId, ticketPrice, numberOfTickets, "NOT-Used", ticketName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean decreaseTicketType(int eventId, int ticketTypeId) {
        String sql = "UPDATE TicketType SET numberOfTickets = numberOfTickets - ? WHERE Event_idEvent = ? AND idTicketType = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setInt(2, eventId);
            ps.setInt(3, ticketTypeId);
            int rowsAffected = ps.executeUpdate();
            connection.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addTicketToCustomer(Customer customer, CustomerTicket customerTicket) {
        String sql = "INSERT INTO CustomerTicket " + "(idCustomerTicket, Customer_idCustomer, TicketType_idTicketType) " + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerTicket.getTicketId());
            ps.setInt(2, customer.getCustomerId());
            ps.setInt(3, customerTicket.getTicketTypeId());

            int rowsAffected = ps.executeUpdate();
            connection.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clearDatabase() {
        try {
            String schema_sql = Files.readString(Path.of("src/main/resources/db/schema.sql"));

            // Split script into individual statements
            executeSqlScript(schema_sql);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void readSeed() {
        try {
            String schema_sql = Files.readString(Path.of("src/main/resources/db/seed.sql"));

            // Split script into individual statements
            executeSqlScript(schema_sql);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void executeSqlScript(String schema_sql) throws SQLException {
        String[] sqlStatements = schema_sql.split(";");

        try (Statement statement = connection.createStatement()) {
            for (String sql : sqlStatements) {
                sql = sql.trim(); // Remove unnecessary spaces
                if (!sql.isEmpty()) { // Ignore empty statements
                    statement.execute(sql);
                }
            }
        }
        connection.commit();
    }

    public void commitChanges() throws SQLException {
        connection.commit();
    }

    public void enableAutoCommit() throws SQLException {
        connection.setAutoCommit(true);
    }

    public void disableAutoCommit() throws SQLException {
        connection.setAutoCommit(false);
    }
}
