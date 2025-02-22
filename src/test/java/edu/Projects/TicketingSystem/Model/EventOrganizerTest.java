package edu.Projects.TicketingSystem.Model;

import edu.Projects.TicketingSystem.Model.EventComponents.Event;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class EventOrganizerTest {

    EventOrganizer CreateEventOrganizer() {
        return new EventOrganizer(1, "mostafa", "1234", "mostafa@gmail.com", "01093826360", 120.0);
    }

    EventOrganizer CreateEventOrganizerWithEvents(int numEvents) {
        EventOrganizer eventOrganizer = CreateEventOrganizer();
        for (int i = 1; i <= numEvents; i++) {
            eventOrganizer.createEvent(i, "Event " + i, "event type", "event description");
        }
        return eventOrganizer;
    }

    @Test
    void getEvents() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithEvents(1);
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, "Event1", "event type", "event description"));
        assertEquals(events, eventOrganizer.getEvents());
    }


    @Test
    void createEvent() {
        EventOrganizer eventOrganizer = CreateEventOrganizer();
        eventOrganizer.createEvent(1, "Event1", "event type", "event description");
        Event event = new Event(1, "Event1", "event type", "event description");
        assertTrue(eventOrganizer.getEvents().contains(event));
    }

    @Test
    void deleteEvent() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithEvents(1);
        eventOrganizer.deleteEvent(eventOrganizer.getEvents().get(0));
        assertEquals(new ArrayList<>(), eventOrganizer.getEvents());
    }

    @Test
    void getIdxByEventId() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithEvents(3);
        eventOrganizer.createEvent(1, "Event1", "event type", "event description");
        assertEquals(0, eventOrganizer.getIdxByEventId(1));
    }

    @Test
    void addTicketForEvent() {
        EventOrganizer eventOrganizer = CreateEventOrganizerWithEvents(1);

        Ticket ticket = new Ticket(1, 1, 100, "ticket", "", LocalDate.now(), false, 10, 10.0);
        Ticket ticket2 = new Ticket(1, 1, 100, "ticket", "", LocalDate.now(), false, 10, 10.0);
        eventOrganizer.addTicketForEvent(1, ticket);

        Event event = eventOrganizer.getEvents().get(eventOrganizer.getIdxByEventId(1));
        assertEquals(event.getTicketByTicketId(1), ticket2);
    }
}