package edu.Projects.TicketingSystem.Service;

import edu.Projects.TicketingSystem.Model.EventComponents.Ticket;
import edu.Projects.TicketingSystem.Service.DataHandlerInterfaces.DataHandler;

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

    //TODO createEvent
    public boolean createEvent() {
        return false;
    }

    //TODO add Ticket
    public boolean addTicket() {
        return false;
    }

}
