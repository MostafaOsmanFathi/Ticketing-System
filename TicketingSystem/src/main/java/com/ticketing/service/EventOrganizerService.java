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

    private EventOrganizerService(DatabaseRepository databaseRepository) {
        ticketingRepository = databaseRepository;
    }

    public static EventOrganizerService getInstance(DatabaseRepository databaseRepository) {
        if (instance == null) {
            instance = new EventOrganizerService(databaseRepository);
        }
        return instance;
    }

    public boolean addEvent(EventOrganizer eventOrganizer, Event event) {
        return ticketingRepository.createEvent(eventOrganizer, event)
                && eventOrganizer.addEvent(event);
    }

    public boolean addTicketTypeToEvent(Event event, TicketType ticketType) {
        return event.addTicketType(ticketType) &&
                ticketingRepository.createTicketType(event, ticketType);
    }

    public TicketType getTicketTypeFromEvent(Event event, int ticketTypeId) {
        return ticketingRepository.getTicketType(event.getEventId(), ticketTypeId);
    }
}
