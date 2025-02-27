package edu.Projects.TicketingSystem.Service.DataHandlerInterfaces;

import edu.Projects.TicketingSystem.Model.EventComponents.Event;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import edu.Projects.TicketingSystem.Model.EventOrganizer;

import java.util.List;

public interface EventOrganizerHandler {

    List<Ticket> getEventOrganizerTickets(EventOrganizer eventOrganizer);

    List<Ticket> getAllAvailableTickets();

    boolean addTicketToEvent(Event event, Ticket ticket);

    boolean addEvent(EventOrganizer eventOrganizer, Event event);

}
