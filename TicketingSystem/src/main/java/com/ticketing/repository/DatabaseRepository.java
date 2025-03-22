package com.ticketing.repository;

import com.ticketing.model.*;

public class DatabaseRepository implements AccountRepository, TicketingRepository {
    private static DatabaseRepository instance;

    public static DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepository();
        }
        return instance;
    }

    private DatabaseRepository() {
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
    public boolean withdraw(Account account, double amount) {
        return false;
    }

    @Override
    public boolean createEvent(EventOrganizer eventOrganizer, Event event) {
        return false;
    }

    @Override
    public boolean getEvent(int eventId) {
        return false;
    }

    @Override
    public boolean createTicketType(Event event, TicketType ticketType) {
        return false;
    }

    @Override
    public boolean getTicketType(int eventId, int ticketTypeId) {
        return false;
    }

    @Override
    public boolean addTicketToCustomer(Customer customer, CustomerTicket customerTicket) {
        return false;
    }
}
