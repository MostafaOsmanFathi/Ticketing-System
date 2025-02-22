package edu.Projects.TicketingSystem.Model.EventComponents;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketInfoTest {

    @Test
    void testEquals() {
        TicketInfo ticket1 = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        TicketInfo ticket2 = new TicketInfo(1, 100, 60.0, "Regular", "Standard", LocalDate.of(2025, 12, 31));
        TicketInfo ticket3 = new TicketInfo(2, 101, 70.0, "General", "Basic", LocalDate.of(2025, 12, 31));

        assertEquals(ticket1, ticket2); // Same ticketID and eventID, should be equal
        assertNotEquals(ticket1, ticket3); // Different ticketID and eventID, should not be equal
    }

    @Test
    void testHashCode() {
        TicketInfo ticket1 = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        TicketInfo ticket2 = new TicketInfo(1, 100, 60.0, "Regular", "Standard", LocalDate.of(2025, 12, 31));

        assertEquals(ticket1.hashCode(), ticket2.hashCode()); // Hash codes should match for equal objects
    }

    @Test
    void testIsValidForNow() {
        TicketInfo validTicket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.now().plusDays(10));
        TicketInfo expiredTicket = new TicketInfo(2, 101, 50.0, "General", "Basic", LocalDate.now().minusDays(1));

        assertTrue(validTicket.isValidForNow());
        assertFalse(expiredTicket.isValidForNow());
    }

    @Test
    void testGetTicketID() {
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals(1, ticket.getTicketID());
    }

    @Test
    void testGetEventID() {
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals(100, ticket.getEventID());
    }

    @Test
    void testGetTicketPrice() {
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals(50.0, ticket.getTicketPrice());
    }

    @Test
    void testGetTicketName() {
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals("VIP", ticket.getTicketName());
    }

    @Test
    void testGetTicketType() {
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", LocalDate.of(2025, 12, 31));
        assertEquals("Premium", ticket.getTicketType());
    }

    @Test
    void testGetExpirationDate() {
        LocalDate expectedDate = LocalDate.of(2025, 12, 31);
        TicketInfo ticket = new TicketInfo(1, 100, 50.0, "VIP", "Premium", expectedDate);
        assertEquals(expectedDate, ticket.getExpirationDate());
    }
}
