package com.ticketing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventOrganizerTest {
    private EventOrganizer eventOrganizer;

    @BeforeEach
    void setUp() {
        eventOrganizer = new EventOrganizer(1, 100, "OrganizerName", "org@example.com", "password");
    }

    @Test
    void getEventOrganizerId() {
        assertEquals(100, eventOrganizer.getEventOrganizerId());
    }

    @Test
    void addEvent() {
        Event event = new Event(10, 100, "EventName", "EventType", "EventDescription");
        assertTrue(eventOrganizer.addEvent(event));
    }
}
