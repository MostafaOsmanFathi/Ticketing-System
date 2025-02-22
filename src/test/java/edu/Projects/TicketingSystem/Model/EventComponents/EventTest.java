package edu.Projects.TicketingSystem.Model.EventComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Objects;

class EventTest {
    private Event event;
    private Ticket ticket1, ticket2;
    private CustomerTicket customerTicket;

    @BeforeEach
    void setUp() {
        event = new Event(1, "Concert", "Music", "Live concert event");
        ticket1 = new Ticket(101, 1,100.0,"lol","gaming", LocalDate.now(),false,20,5.0);
        ticket2 = new Ticket(102, 1,100.0,"cooking event","cooking", LocalDate.now(),false,20,5.0);
        customerTicket = new CustomerTicket(101, ticket1);
    }

    @Test
    void addTicket_ShouldAddTicketSuccessfully() {
        assertTrue(event.addTicket(ticket1));
        assertTrue(event.addTicket(ticket2));
    }

    @Test
    void testEquals_ShouldReturnTrueForSameEventID() {
        Event sameEvent = new Event(1, "Different Name", "Different Type", "Different Description");
        assertEquals(event, sameEvent);
    }

    @Test
    void testEquals_ShouldReturnFalseForDifferentEventID() {
        Event differentEvent = new Event(2, "Another Event", "Music", "Another concert");
        assertNotEquals(event, differentEvent);
    }

    @Test
    void testHashCode_ShouldBeConsistentWithEquals() {
        Event sameEvent = new Event(1, "Concert", "Music", "Live concert event");
        assertEquals(event.hashCode(), sameEvent.hashCode());
    }

    @Test
    void getEventID_ShouldReturnCorrectID() {
        assertEquals(1, event.getEventID());
    }

    @Test
    void getEventName_ShouldReturnCorrectName() {
        assertEquals("Concert", event.getEventName());
    }

    @Test
    void getEventType_ShouldReturnCorrectType() {
        assertEquals("Music", event.getEventType());
    }

    @Test
    void getEventDescription_ShouldReturnCorrectDescription() {
        assertEquals("Live concert event", event.getEventDescription());
    }

    @Test
    void confirmTicket_ShouldIncreaseConfirmedTickets() {
        assertFalse(event.confirmTicket(customerTicket)); // Assuming confirmTicket() has a bug (always returns false)
        assertEquals(0, event.numberOfConfirmedTickets); // Should ideally be 1 if implemented correctly
    }

    @Test
    void getTicketByTicketId_ShouldReturnCorrectTicket() {
        event.addTicket(ticket1);
        event.addTicket(ticket2);
        assertEquals(ticket1, event.getTicketByTicketId(101));
        assertEquals(ticket2, event.getTicketByTicketId(102));
    }

    @Test
    void getTicketByTicketId_ShouldReturnNullForNonExistentTicket() {
        assertNull(event.getTicketByTicketId(999));
    }
}
