package com.ticketing.service;

import com.ticketing.model.Event;
import com.ticketing.model.EventOrganizer;
import com.ticketing.model.TicketType;
import com.ticketing.repository.AccountRepository;
import com.ticketing.repository.DatabaseRepository;
import com.ticketing.repository.TicketingRepository;

public class EventOrganizerService {
    private static TicketingRepository ticketingRepository;
    private static EventOrganizerService instance;

    private EventOrganizerService() {
        ticketingRepository = DatabaseRepository.getInstance();
    }

    public static EventOrganizerService getInstance() {
        if (instance == null) {
            instance = new EventOrganizerService();
        }
        return instance;
    }

    boolean addEvent(EventOrganizer eventOrganizer, Event event) {
        return ticketingRepository.createEvent(eventOrganizer, event)
                && eventOrganizer.addEvent(event);
    }

    boolean addTicketTypeToEvent(Event event, TicketType ticketType) {
        return event.addTicketType(ticketType) &&
                ticketingRepository.createTicketType(event, ticketType);
    }

    TicketType getTicketTypeFromEvent(Event event, int ticketTypeId) {
        return ticketingRepository.getTicketType(event.getEventId(), ticketTypeId);
    }
}
