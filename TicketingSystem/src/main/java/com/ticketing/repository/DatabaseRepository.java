package com.ticketing.repository;

import com.ticketing.model.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseRepository implements AccountRepository, TicketingRepository {
    private static DatabaseRepository instance;
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public static DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepository();
        }
        return instance;
    }

    private DatabaseRepository() {
        try {
            loadConfiguration();
            this.connection = DriverManager.getConnection(url, user, password);
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
                return new Account(rs.getInt("idAccount"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean depositToAccountByAccountId(int accountId, double amount) {
        String sql = "UPDATE Account SET balance = balance + ? WHERE idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean withdrawAccountById(int accountId, double amount) {
        String sql = "UPDATE Account SET balance = balance - ? WHERE idAccount = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;
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
            return stmt.executeUpdate() > 0;
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

    public boolean addTicketToCustomer(Customer customer, CustomerTicket customerTicket) {
        String sql = "INSERT INTO CustomerTicket " + "(idCustomerTicket, Customer_idCustomer, TicketType_idTicketType) " + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerTicket.getTicketId());
            ps.setInt(2, customer.getCustomerId());
            ps.setInt(3, customerTicket.getTicketTypeId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
