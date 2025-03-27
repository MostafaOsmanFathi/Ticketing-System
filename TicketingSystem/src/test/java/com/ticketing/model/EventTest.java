package com.ticketing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event(1, 10, "Concert", "Music", "Live performance");
    }

    @Test
    void getEventOrganizerId() {
        assertEquals(10, event.getEventOrganizerId());
    }

    @Test
    void addTicketType() {
        TicketType ticketType = new TicketType(1, 10, 100, 50.0, 10, "VIP", "VIP Ticket");
        assertTrue(event.addTicketType(ticketType));
    }

    @Test
    void getTicketTypes() {
        TicketType ticketType = new TicketType(1, 10, 100, 50.0, 10, "VIP", "VIP Ticket");
        event.addTicketType(ticketType);
        TicketType retrieved = event.getTicketTypes(0);
        assertEquals(ticketType, retrieved);
        assertThrows(IndexOutOfBoundsException.class, () -> event.getTicketTypes(1));
    }

    @Test
    void getEventName() {
        assertEquals("Concert", event.getEventName());
    }

    @Test
    void setEventName() {
        event.setEventName("Festival");
        assertEquals("Festival", event.getEventName());
    }

    @Test
    void getEventType() {
        assertEquals("Music", event.getEventType());
    }

    @Test
    void setEventType() {
        event.setEventType("Art");
        assertEquals("Art", event.getEventType());
    }

    @Test
    void getEventDescription() {
        assertEquals("Live performance", event.getEventDescription());
    }

    @Test
    void setEventDescription() {
        event.setEventDescription("Annual live performance");
        assertEquals("Annual live performance", event.getEventDescription());
    }

    @Test
    void getEventId() {
        assertEquals(1, event.getEventId());
    }
}
