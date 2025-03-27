package com.ticketing.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTypeTest {

    @Test
    void generateCustomerTicket() {
        TicketType ticketType = new TicketType(100, 50, 10, 25.0, 5, "VIP", "VIP Ticket");
        int initialNumber = ticketType.getNumberOfTickets();
        CustomerTicket ticket = ticketType.generateCustomerTicket(200);
        assertNotNull(ticket);
        assertEquals(10, ticket.getTicketTypeId());
        assertEquals(100, ticket.getEventId());
        assertEquals(200, ticket.getCustomerId());
        assertEquals(initialNumber - 1, ticketType.getNumberOfTickets());
        TicketType emptyTicketType = new TicketType(101, 51, 11, 30.0, 0, "Regular", "Regular Ticket");
        Exception exception = assertThrows(IllegalStateException.class, () -> emptyTicketType.generateCustomerTicket(201));
        assertEquals("There no more tickets", exception.getMessage());
    }

    @Test
    void getEventOrgnizerId() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals(55, ticketType.getEventOrgnizerId());
    }

    @Test
    void increaseNumberOfTicket() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        int prev = ticketType.getNumberOfTickets();
        assertTrue(ticketType.increaseNumberOfTicket());
        assertEquals(prev + 1, ticketType.getNumberOfTickets());
    }

    @Test
    void decreaseNumberOfTicket() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        int prev = ticketType.getNumberOfTickets();
        assertTrue(ticketType.decreaseNumberOfTicket());
        assertEquals(prev - 1, ticketType.getNumberOfTickets());
    }

    @Test
    void getTicketPrice() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals(25.0, ticketType.getTicketPrice());
    }

    @Test
    void getTicketType() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals("VIP", ticketType.getTicketType());
    }

    @Test
    void getTicketName() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals("VIP Ticket", ticketType.getTicketName());
    }

    @Test
    void getTicketTypeId() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals(10, ticketType.getTicketTypeId());
    }

    @Test
    void setTicketPrice() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        ticketType.setTicketPrice(30.0);
        assertEquals(30.0, ticketType.getTicketPrice());
    }

    @Test
    void setTicketName() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        ticketType.setTicketName("Premium VIP Ticket");
        assertEquals("Premium VIP Ticket", ticketType.getTicketName());
    }

    @Test
    void setTicketType() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        ticketType.setTicketType("Premium");
        assertEquals("Premium", ticketType.getTicketType());
    }

    @Test
    void getEventId() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals(100, ticketType.getEventId());
    }

    @Test
    void getNumberOfTickets() {
        TicketType ticketType = new TicketType(100, 55, 10, 25.0, 5, "VIP", "VIP Ticket");
        assertEquals(5, ticketType.getNumberOfTickets());
    }
}
