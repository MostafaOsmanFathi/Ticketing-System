package edu.Projects.TicketingSystem.Model.EventComponents;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTicketTest {

    @Test
    void testCustomerTicketConstructor() {
        CustomerTicket ticket = new CustomerTicket(1, 10, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals(1, ticket.customerID);
        assertEquals(10, ticket.getTicketID());
        assertEquals(100, ticket.getEventID());
        assertEquals(50.0, ticket.getTicketPrice());
        assertEquals("VIP", ticket.getTicketName());
        assertEquals("Premium", ticket.getTicketType());
        assertEquals(LocalDate.of(2025, 12, 31), ticket.getExpirationDate());
    }

    @Test
    void testConfirmTicket() {
        CustomerTicket validTicket = new CustomerTicket(1, 10, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertTrue(validTicket.confirmTicket());
        assertEquals(TicketStatus.Confirmed, validTicket.status);

        CustomerTicket expiredTicket = new CustomerTicket(2, 20, 200, 60.0, "Regular", "Standard", LocalDate.of(2023, 1, 1));
        assertFalse(expiredTicket.confirmTicket());
        assertEquals(TicketStatus.NOtValid, expiredTicket.status);
    }
}