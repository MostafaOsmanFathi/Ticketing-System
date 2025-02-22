package edu.Projects.TicketingSystem.Model.EventComponents;

import edu.Projects.TicketingSystem.Model.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testIsTicketRefundable() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertTrue(ticket.isTicketRefundable());
    }

    @Test
    void testGetRefundRate() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertEquals(0.8, ticket.getRefundRate());
    }

    @Test
    void testGetRefundCost() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertEquals(40.0, ticket.getRefundCost());
    }

    @Test
    void testGetNumberOfTickets() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertEquals(5, ticket.getNumberOfTickets());
    }

    @Test
    void testIncreaseCount() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertTrue(ticket.increaseCount());
        assertEquals(6, ticket.getNumberOfTickets());
    }

    @Test
    void testDecreaseCount() {
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 5, 0.8);
        assertTrue(ticket.decreaseCount());
        assertEquals(4, ticket.getNumberOfTickets());
    }

    @Test
    void testCreateCustomerTicket() {
        Customer customer = new Customer(30, "mostafa", "1234", "mostafa@gmail.com", "01093826360", 120.0);
        Ticket ticket = new Ticket(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31), true, 1, 0.8);

        assertNotNull(ticket.createCustomerTicket(customer));
        assertEquals(0, ticket.getNumberOfTickets());

        Ticket emptyTicket = new Ticket(2, 101, 60.0, "Regular", "Standard", LocalDate.of(2025, 12, 31), true, 0, 0.5);
        assertNull(emptyTicket.createCustomerTicket(customer));
    }
}
