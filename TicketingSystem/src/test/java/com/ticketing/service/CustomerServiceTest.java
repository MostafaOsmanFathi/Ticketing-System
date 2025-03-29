package com.ticketing.service;

import com.ticketing.model.*;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO to be implemented
class CustomerServiceTest {
    private CustomerService customerService;
    private AccountService accountService;
    private Customer customer;
    private EventOrganizer eventOrganizer;
    private Event event;
    private TicketType ticketType;
    private DatabaseRepository databaseRepository;



    @BeforeEach
    void setUp() {
        accountService = new AccountService(MySqlRepository.getInstance());
        databaseRepository = MySqlRepository.getInstance();
        databaseRepository.resetDatabase();
        customerService = CustomerService.getInstance(MySqlRepository.getInstance());
        customer = new Customer(1, 1, "John Doe", "john@example.com", "password");
        customer.deposit(500.0);
        eventOrganizer = new EventOrganizer(2, 2, "Mostafa", "Mostafa@example.com", "password");
        event = new Event(1, 2, "ECPC", "Problem Solving", "problem Solving contest");
        eventOrganizer.addEvent(event);
        ticketType = new TicketType(1, 2, 1, 100.0, 10, "Premium", "PS11");
        event.addTicketType(ticketType);


        databaseRepository.createCustomerAccount(customer);

        databaseRepository.createEventOrganizerAccount(eventOrganizer);
        databaseRepository.createEvent(eventOrganizer, event);

        databaseRepository.createTicketType(event, ticketType);
    }

    @Test
    void getInstance_shouldReturnSameInstance() {
        CustomerService instance1 = CustomerService.getInstance(MySqlRepository.getInstance());
        CustomerService instance2 = CustomerService.getInstance(MySqlRepository.getInstance());
        assertSame(instance1, instance2, "getInstance should return the same instance (Singleton pattern)");
    }

    @Test
    void buyTicket_shouldSucceedWhenCustomerHasEnoughBalance() {
        assertTrue(customerService.buyTicket(customer, ticketType));
        assertEquals(400.0, customer.getWalletBalance(), "Customer balance should decrease after buying a ticketType");
    }

    @Test
    void buyTicket_shouldFailWhenCustomerHasInsufficientBalance() {
        accountService.login("john@example.com", "password");
        accountService.withdraw(450.0);
        assertFalse(customerService.buyTicket(customer, ticketType));
    }
}
