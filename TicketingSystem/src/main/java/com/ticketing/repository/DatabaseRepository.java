package com.ticketing.repository;

import com.ticketing.enums.AccountType;
import com.ticketing.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseRepository implements AccountRepository, TicketingRepository {
    protected String url;
    protected Connection connection;
    protected String defaultSchema;

    public DatabaseRepository(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    @Override
    public boolean createAccount(Account account, String accountType) {
        String insertAccountSQL = "INSERT INTO Account (name, email, password, balance) VALUES (?, ?, ?, ?)";
        String insertRoleSQL = accountType.equalsIgnoreCase("Customer") ? "INSERT INTO Customer (Account_idAccount) VALUES (?)" : "INSERT INTO EventOrganizer (Account_idAccount) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertAccountSQL, Statement.RETURN_GENERATED_KEYS); PreparedStatement stmtRole = connection.prepareStatement(insertRoleSQL, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);

            // Insert into Account table
            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getPassword());
            stmt.setDouble(4, account.getWalletBalance());
            stmt.executeUpdate();

            // Retrieve the generated account ID
            int accountId;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    accountId = generatedKeys.getInt(1); // Get the auto-generated primary key
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

            account.setAccountId(accountId);

            stmtRole.setInt(1, accountId);
            stmtRole.executeUpdate();

            int customerOrEventOrgId = -1;
            try (ResultSet generatedKeysRole = stmtRole.getGeneratedKeys()) {
                if (generatedKeysRole.next()) {
                    customerOrEventOrgId = generatedKeysRole.getInt(1); // Get the auto-generated primary key of the second insert
                    if (account instanceof Customer customer) {
                        customer.setCustomerId(customerOrEventOrgId);
                    } else if (account instanceof EventOrganizer eventOrganizer) {
                        eventOrganizer.setEventOrganizerId(customerOrEventOrgId);
                    }
                } else {
                    throw new SQLException("Creating role failed, no ID obtained.");
                }
            }

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
                if (walletBalance != 0) account.deposit(walletBalance);
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
        String query = "INSERT INTO Event (eventName, eventType, eventDescription, EventOrganizer_idEventOrganizer) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, event.getEventName());
            stmt.setString(2, event.getEventType());
            stmt.setString(3, event.getEventDescription());
            stmt.setInt(4, eventOrganizer.getEventOrganizerId());

            boolean affectedRows = stmt.executeUpdate() > 0;

            // Retrieve the generated event ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setEventId(generatedKeys.getInt(1)); // Update event object with generated ID
                }
            }

            connection.commit();
            return affectedRows;
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
        String sql = "INSERT INTO TicketType (ticketPrice, ticketName, numberOfTickets, Event_idEvent,EventOrganizer_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, ticketType.getTicketPrice());
            ps.setString(2, ticketType.getTicketName());
            ps.setInt(3, ticketType.getNumberOfTickets());
            ps.setInt(4, event.getEventId());
            ps.setInt(5, event.getEventOrganizerId());

            int rowsAffected = ps.executeUpdate();

            // Retrieve the generated ticket ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticketType.setTicketTypeId(generatedKeys.getInt(1)); // Set the generated ID
                }
            }

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
        String sql = "INSERT INTO CustomerTicket (Customer_idCustomer, TicketType_idTicketType) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customer.getCustomerId());
            ps.setInt(2, customerTicket.getTicketTypeId());

            int rowsAffected = ps.executeUpdate();

            // Retrieve the generated ticket ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customerTicket.setTicketId(generatedKeys.getInt(1)); // Store generated ID in the object
                }
            }

            connection.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void resetDatabase() {
        try {
            String schema_sql = Files.readString(Path.of("src/main/resources/db/" + defaultSchema));

            // Split script into individual statements
            executeSqlScript(schema_sql);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void clearDatabase() {
        try {
            String schema_sql = "DROP DATABASE IF EXISTS TicketingSystem;";

            executeSqlScript(schema_sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        resetDatabase();
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

    protected void createDatabaseIfNotExists() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + "TicketingSystem");
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getEventsForEventOrganizer(int eventOrganizerId) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Event WHERE EventOrganizer_idEventOrganizer = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventOrganizerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event(resultSet.getInt("idEvent"), resultSet.getInt("EventOrganizer_idEventOrganizer"), resultSet.getString("eventName"), resultSet.getString("eventType"), resultSet.getString("eventDescription"));

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Event";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event(resultSet.getInt("idEvent"), resultSet.getInt("EventOrganizer_idEventOrganizer"), resultSet.getString("eventName"), resultSet.getString("eventType"), resultSet.getString("eventDescription"));

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<TicketType> getTicketTypesByEventId(int eventId) {
        List<TicketType> ticketTypes = new ArrayList<>();
        String query = "SELECT * FROM TicketType WHERE Event_idEvent = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TicketType ticketType = new TicketType(resultSet.getInt("Event_idEvent"), resultSet.getInt("EventOrganizer_id"), resultSet.getInt("idTicketType"), resultSet.getDouble("ticketPrice"), resultSet.getInt("numberOfTickets"), "----", resultSet.getString("ticketName")

                );
                ticketTypes.add(ticketType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketTypes;
    }

    public List<TicketType> getAllTicketTypes() {
        List<TicketType> ticketTypes = new ArrayList<>();
        String query = "SELECT * FROM TicketType";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TicketType ticketType = new TicketType(resultSet.getInt("Event_idEvent"), resultSet.getInt("EventOrganizer_id"), resultSet.getInt("idTicketType"), resultSet.getDouble("ticketPrice"), resultSet.getInt("numberOfTickets"), "", ""

                );
                ticketTypes.add(ticketType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketTypes;
    }

    public List<CustomerTicket> getCustomerTicketsByCustomerId(int customerId) {
        List<CustomerTicket> customerTickets = new ArrayList<>();

        String query = "SELECT * FROM CustomerTicket JOIN TicketType ON CustomerTicket.TicketType_idTicketType = TicketType.idTicketType WHERE CustomerTicket.idCustomerTicket = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerTicket customerTicket = new CustomerTicket(
                        resultSet.getInt("idCustomerTicket"),
                        resultSet.getInt("Customer_idCustomer"),
                        resultSet.getInt("TicketType_idTicketType"),
                        resultSet.getInt("Event_idEvent")
                );
                customerTickets.add(customerTicket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerTickets;
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
