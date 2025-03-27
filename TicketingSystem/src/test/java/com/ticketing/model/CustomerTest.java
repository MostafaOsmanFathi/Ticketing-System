package com.ticketing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Create a Customer with accountId 100, customerId 200, and sample details.
        customer = new Customer(100, 200, "Alice", "alice@example.com", "securepass");
    }

    @Test
    void getCustomerId() {
        // Verify that the customerId is returned correctly.
        assertEquals(200, customer.getCustomerId());
    }

    @Test
    void testCustomerTicketFields() {
        // Create a CustomerTicket with sample values:
        // ticketId = 1, customerId = 100, ticketTypeId = 10, eventId = 200.
        CustomerTicket ticket = new CustomerTicket(1, 100, 10, 200);
        // Verify that each getter returns the expected value.
        assertEquals(1, ticket.getTicketId());
        assertEquals(10, ticket.getTicketTypeId());
        assertEquals(100, ticket.getCustomerId());
        assertEquals(200, ticket.getEventId());
    }
}
