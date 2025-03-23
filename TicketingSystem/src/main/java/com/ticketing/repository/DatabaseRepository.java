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
        return false;
    }

    @Override
    public Account getAccount(String email, String password) {
        return null;
    }

    @Override
    public boolean deposit(Account account, double amount) {
        return false;
    }

    @Override
    public boolean depositToAccountByAccountId(int accountId, double amount) {
        return false;
    }

    @Override
    public boolean withdraw(Account account, double amount) {
        return false;
    }

    @Override
    public boolean createEventOrganizerAccount(Account account) {
        return AccountRepository.super.createEventOrganizerAccount(account);
    }

    @Override
    public boolean createEventCustomerAccount(Account account) {
        return AccountRepository.super.createEventCustomerAccount(account);
    }

    @Override
    public boolean createEvent(EventOrganizer eventOrganizer, Event event) {
        return false;
    }

    @Override
    public Event getEvent(int eventOrganizerId, int eventId) {
        return null;
    }

    @Override
    public boolean createTicketType(Event event, TicketType ticketType) {
        return false;
    }

    @Override
    public TicketType getTicketType(int eventId, int ticketTypeId) {
        return null;
    }

    @Override
    public boolean addTicketToCustomer(Customer customer, CustomerTicket customerTicket) {
        return false;
    }
}
