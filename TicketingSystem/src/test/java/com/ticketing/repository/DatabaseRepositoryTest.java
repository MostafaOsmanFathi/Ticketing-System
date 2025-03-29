package com.ticketing.repository;

import com.ticketing.model.Account;
import com.ticketing.model.Customer;
import com.ticketing.model.CustomerTicket;
import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseRepositoryTest {
    DatabaseRepository repository;

    @BeforeEach
    void getInstance() {
        repository = MySqlRepository.getInstance();
        repository.clearDatabase();
    }

    @Test
    void createAccount() {
        Account account = new Account(1001, "User1", "user1@example.com", "pass1");
        boolean result = repository.createAccount(account, "Customer");
        assertTrue(result);
    }

    @Test
    void getAccount() {
        Account account = new Account(1002, "User2", "user2@example.com", "pass2");
        repository.createAccount(account, "Customer");
        Account result = repository.getAccount("user2@example.com", "pass2");
        assertNotNull(result);
        assertEquals(1002, result.getAccountId());
    }

    @Test
    void depositToAccountByAccountId() {
        Account account = new Account(1003, "User3", "user3@example.com", "pass3");
        repository.createAccount(account, "Customer");
        boolean depositResult = repository.depositToAccountByAccountId(1003, 50.0);
        assertTrue(depositResult);
        Account updated = repository.getAccount("user3@example.com", "pass3");
        assertEquals(50.0, updated.getWalletBalance());
    }

    @Test
    void withdrawAccountById() {
        Account account = new Account(1004, "User4", "user4@example.com", "pass4");
        repository.createAccount(account, "Customer");
        repository.depositToAccountByAccountId(1004, 100.0);
        boolean withdrawResult = repository.withdrawAccountById(1004, 40.0);
        assertTrue(withdrawResult);
        Account updated = repository.getAccount("user4@example.com", "pass4");
        assertEquals(60.0, updated.getWalletBalance());
    }

    @Test
    void createEvent() {
        Account organizerAccount = new Account(2001, "Org1", "org1@example.com", "orgpass1");
        repository.createAccount(organizerAccount, "EventOrganizer");
        EventOrganizer organizer = new EventOrganizer(2001, 2001, "Org1", "org1@example.com", "orgpass1");
        Event event = new Event(3001, 2001, "Event1", "Type1", "Description1");
        boolean result = repository.createEvent(organizer, event);
        assertTrue(result);
    }

    @Test
    void getEvent() {
        Account organizerAccount = new Account(2002, "Org2", "org2@example.com", "orgpass2");
        repository.createAccount(organizerAccount, "EventOrganizer");
        EventOrganizer organizer = new EventOrganizer(2002, 2002, "Org2", "org2@example.com", "orgpass2");
        Event event = new Event(3002, 2002, "Event2", "Type2", "Description2");
        repository.createEvent(organizer, event);
        Event result = repository.getEvent(2002, 3002);
        assertNotNull(result);
        assertEquals("Event2", result.getEventName());
    }

    @Test
    void createTicketType() {
        Account organizerAccount = new Account(2003, "Org3", "org3@example.com", "orgpass3");
        repository.createAccount(organizerAccount, "EventOrganizer");
        EventOrganizer organizer = new EventOrganizer(2003, 2003, "Org3", "org3@example.com", "orgpass3");
        Event event = new Event(3003, 2003, "Event3", "Type3", "Description3");
        repository.createEvent(organizer, event);
        TicketType ticketType = new TicketType(3003, 2003, 4001, 75.0, 100, "General", "General Admission");
        boolean result = repository.createTicketType(event, ticketType);
        assertTrue(result);
    }

    @Test
    void getTicketType() {
        Account organizerAccount = new Account(2004, "Org4", "org4@example.com", "orgpass4");
        repository.createAccount(organizerAccount, "EventOrganizer");
        EventOrganizer organizer = new EventOrganizer(2004, 2004, "Org4", "org4@example.com", "orgpass4");
        Event event = new Event(3004, 2004, "Event4", "Type4", "Description4");
        repository.createEvent(organizer, event);
        TicketType ticketType = new TicketType(3004, 2004, 4002, 85.0, 150, "VIP", "VIP Admission");
        repository.createTicketType(event, ticketType);
        TicketType result = repository.getTicketType(3004, 4002);
        assertNotNull(result);
        assertEquals(85.0, result.getTicketPrice());
    }

    @Test
    void addTicketToCustomer() {
        repository.clearDatabase();
        Account account = new Account(1005, "User5", "user5@example.com", "pass5");
        repository.createAccount(account, "Customer");
        Customer customer = new Customer(1005, 1005, "User5", "user5@example.com", "pass5");
        Account organizerAccount = new Account(2005, "Org5", "org5@example.com", "orgpass5");
        repository.createAccount(organizerAccount, "EventOrganizer");
        EventOrganizer organizer = new EventOrganizer(2005, 2005, "Org5", "org5@example.com", "orgpass5");
        Event event = new Event(3005, 2005, "Event5", "Type5", "Description5");
        repository.createEvent(organizer, event);
        TicketType ticketType = new TicketType(3005, 2005, 4005, 50.0, 100, "Regular", "Regular Ticket");
        repository.createTicketType(event, ticketType);
        CustomerTicket ticket = new CustomerTicket(5005, 1005, 4005, 3005);
        boolean result = repository.addTicketToCustomer(customer, ticket);
        assertTrue(result);
    }

    @Test
    void clearDatabase() {
        repository.clearDatabase();
    }

    @Test
    void readSeed() {
        repository.readSeed();
    }

}
