package com.ticketing.repository;

import com.ticketing.model.*;

public interface TicketingRepository {
    /// Event Organizer operations
    boolean createEvent(EventOrganizer eventOrganizer, Event event);

    Event getEvent(int eventOrganizerId, int eventId);

    boolean createTicketType(Event event, TicketType ticketType);

    TicketType getTicketType(int eventId, int ticketTypeId);

    boolean decreaseTicketType(int eventId, int ticketTypeId);

    /// Customer Organizer operations
    boolean addTicketToCustomer(Customer customer, CustomerTicket customerTicket);

}
