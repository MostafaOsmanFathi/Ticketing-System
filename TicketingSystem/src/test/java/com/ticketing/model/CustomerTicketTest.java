package com.ticketing.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTicketTest {

    @Test
    void getTicketId() {
        CustomerTicket ticket = new CustomerTicket(1, 101, 10, 200);
        assertEquals(1, ticket.getTicketId());
    }

    @Test
    void getTicketTypeId() {
        CustomerTicket ticket = new CustomerTicket(2, 102, 15, 201);
        assertEquals(15, ticket.getTicketTypeId());
    }

    @Test
    void getCustomerId() {
        CustomerTicket ticket = new CustomerTicket(3, 103, 20, 202);
        assertEquals(103, ticket.getCustomerId());
    }

    @Test
    void getEventId() {
        CustomerTicket ticket = new CustomerTicket(4, 104, 25, 250);
        assertEquals(250, ticket.getEventId());
    }
}
