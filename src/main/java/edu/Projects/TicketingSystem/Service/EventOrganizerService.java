package edu.Projects.TicketingSystem.Service;

import edu.Projects.TicketingSystem.Model.EventComponents.Event;
import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import edu.Projects.TicketingSystem.Model.EventOrganizer;
import edu.Projects.TicketingSystem.Service.DataHandlerInterfaces.DataHandler;

import javax.xml.crypto.Data;
import java.util.List;

public class EventOrganizerService {

    static EventOrganizerService eventOrganizerService = null;
    static DataHandler dataHandler = null;

    private EventOrganizerService() {
    }

    public static EventOrganizerService getEventOrganizerService(DataHandler dataHandlerPram) {
        if (eventOrganizerService == null) {
            eventOrganizerService = new EventOrganizerService();
            dataHandler = dataHandlerPram;
        }
        return eventOrganizerService;
    }

    public boolean createEvent(EventOrganizer eventOrganizer, Event event) {
        if (dataHandler.addEvent(eventOrganizer, event)) {
            eventOrganizer.createEvent(event);
            return true;
        }
        return false;
    }

    public boolean addTicket(Event event, Ticket ticket) {
        if (dataHandler.addTicketToEvent(event, ticket)) {
            event.addTicket(ticket);
            return true;
        }
        return false;
    }

    public List<Ticket> getAllAvlibleTickets() {
        return dataHandler.getAllAvailableTickets();
    }

}
